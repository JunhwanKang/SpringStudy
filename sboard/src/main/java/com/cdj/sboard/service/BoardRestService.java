package com.cdj.sboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cdj.sboard.dao.AttachmentDao;
import com.cdj.sboard.dao.BoardDao;
import com.cdj.sboard.dao.CommentDao;
import com.cdj.sboard.dao.UserDao;
import com.cdj.sboard.dto.CKResponse;
import com.cdj.sboard.entity.Attachment;
import com.cdj.sboard.entity.Board;
import com.cdj.sboard.entity.Comment;

@Service
public class BoardRestService {
	@Value("c:/upload/image")
	private String imageFolder;
	@Value("http://localhost:8081/image/")
	private String imagePath;
	@Value("http://localhost:8081/profile/")
	private String profilePath;
	@Value("c:/upload/attachment")
	private String attachmentFolder;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private AttachmentDao attachmentDao;
	@Autowired
	private CommentDao commentDao;
	
	public CKResponse ckImageUpload(MultipartFile image) throws IllegalStateException, IOException {
		if(image!=null && image.isEmpty()==false) {
			if(image.getContentType().toLowerCase().startsWith("image/")) {
				String imageName = UUID.randomUUID().toString() + ".jpg";
				File file = new File(imageFolder, imageName);
				image.transferTo(file);
				return new CKResponse(1, imageName, imagePath + imageName);
			}
		}
		return null;
	}
	
	public List<Comment> writeComment(Integer bno, Comment comment, String username) {
		String profile = userDao.findById(username).getProfile();
		comment.setWriter(username);
		comment.setProfile(profilePath + profile);
		commentDao.insert(comment);
		boardDao.update(Board.builder().bno(bno).commentCnt(1).build());
		return commentDao.findAllByBno(comment.getBno());
	}

	public List<Attachment> deleteAttachment(Integer ano, Integer bno, String name) {
		Attachment attachment = attachmentDao.findById(ano);
		attachmentDao.delete(ano);
		
		File file = new File(attachmentFolder, attachment.getSaveFileName());
		if(file.exists())
			file.delete();
		return attachmentDao.findAllByBno(bno);
	}

	public List<Comment> deleteComment(Integer cno, Integer bno, String name) {
		commentDao.deleteByCno(cno);
		return commentDao.findAllByBno(bno);
	}

	public List<Attachment> findAttachmentById(Integer ano) {
		return null;
	}

	
}
