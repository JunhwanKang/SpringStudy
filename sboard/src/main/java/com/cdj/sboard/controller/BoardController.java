package com.cdj.sboard.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdj.sboard.dao.BoardDao;
import com.cdj.sboard.dto.BoardDto;
import com.cdj.sboard.entity.Board;
import com.cdj.sboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping({"/", "/board/list"})
	public ModelAndView list(@RequestParam(defaultValue="1") Integer pageNo) {
		return new ModelAndView("main").addObject("viewname", "board/list.jsp")
				.addObject("page", service.list(pageNo));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/board/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewname", "board/write.jsp");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/board/write")
	public ModelAndView write(@ModelAttribute @Valid BoardDto.Write dto, BindingResult bindingResult,
			@RequestParam List<MultipartFile> attachments, Principal principal) throws BindException {
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		int bno = service.write(dto, attachments, principal.getName());
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/board/read")
	public ModelAndView read(@RequestParam Integer bno, Principal principal) {
		String username = (principal==null)? null : principal.getName();
		BoardDto.Read dto = service.easyRead(bno, username);
		return new ModelAndView("main").addObject("viewname", "board/read.jsp").addObject("board", dto);
	}
	
	@PostMapping("/board/deleteContent")
	public ModelAndView deleteContent(Integer bno) {
		service.deleteContent(bno);
		return new ModelAndView("redirect:/");
	}
	
}
