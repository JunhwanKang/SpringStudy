package com.cdj.sboard.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cdj.sboard.dao.AttachmentDao;
import com.cdj.sboard.dao.BoardDao;
import com.cdj.sboard.dao.CommentDao;
import com.cdj.sboard.dto.BoardDto;
import com.cdj.sboard.dto.CommentDto;
import com.cdj.sboard.dto.Page;
import com.cdj.sboard.dto.BoardDto.Read;
import com.cdj.sboard.dto.BoardDto.Write;
import com.cdj.sboard.entity.Attachment;
import com.cdj.sboard.entity.Board;
import com.cdj.sboard.entity.Comment;
import com.cdj.sboard.exception.MvcBoardNotFoundException;
import com.cdj.sboard.util.PaginationUtil;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private AttachmentDao attachmentDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PaginationUtil paginationUtil;
	@Value("http://localhost:8081/profile/")
	private String profilePath;
	@Value("c://upload/attachment")
	private String attachmentFolder;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	
	@Transactional
	public int write(Write dto, List<MultipartFile> attachments, String username) {
		Board board = Board.builder().title(dto.getTitle()).content(dto.getContent()).writer(username)
				.attachmentCnt(attachments.size()).build();
		
		boardDao.insert(board);
		
		for(MultipartFile file : attachments) {
			String saveFileName = System.currentTimeMillis() + "-" +file.getOriginalFilename();
			File saveFile = new File(attachmentFolder, saveFileName);
			try {
				FileCopyUtils.copy(file.getBytes(), saveFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			boolean isImage = file.getContentType().toLowerCase().startsWith("image/");
			Attachment attachment = new Attachment(0, board.getBno(), file.getOriginalFilename(), saveFileName,
					username, file.getSize(), isImage);
			
			attachmentDao.insert(attachment);
		}
		return board.getBno();
	}

	public Read easyRead(int bno, String username) {
		Board board = boardDao.findByBno(bno);
		if(board==null)
			throw new MvcBoardNotFoundException();
		if(username!=null && board.getWriter().equals(username)==false) {
			boardDao.update(Board.builder().bno(bno).readCnt(1).build());
			board.setReadCnt(board.getReadCnt()+1);
		}
		List<Attachment> attachments = attachmentDao.findAllByBno(bno);
		
		List<Comment> commentList = commentDao.findAllByBno(bno);
		List<CommentDto.Read> comments = new ArrayList<CommentDto.Read>();
		for(Comment comment : commentList) {
			CommentDto.Read dto = modelMapper.map(comment, CommentDto.Read.class);
			dto.setWriteTimeString(formatter.format(comment.getWriteTime()));
			dto.setIsWriter(dto.getWriter().equals(username));
			comments.add(dto);
		}
		
		BoardDto.Read dto = modelMapper.map(board, BoardDto.Read.class);
		dto.setIsWriter(board.getWriter().equals(username));
		dto.setWriteTimeString(formatter.format(board.getWriteTime()));
		dto.setAttachments(attachments);
		dto.setComments(comments);
		return dto;
	}

	public Page list(Integer pageNo) {
		int count = boardDao.count();
		List<Board> boardList = boardDao.findAll(paginationUtil.getRowNum(pageNo, count));
		Page page = paginationUtil.getPage(pageNo, count);
		
		List<BoardDto.ListView> list = new ArrayList<BoardDto.ListView>();
		for(Board board : boardList) {
			BoardDto.ListView dto = modelMapper.map(board, BoardDto.ListView.class);
			
			dto.setWriteTimeString(formatter.format(board.getWriteTime()));
			list.add(dto);
		}
		page.setList(list);
		return page;
	}
	
	@Transactional
	public Boolean deleteContent(Integer bno) {
		boardDao.delete(bno);
		commentDao.deleByBno(bno);
		attachmentDao.deleteByBno(bno);
		
		return true;
	}
	
}
