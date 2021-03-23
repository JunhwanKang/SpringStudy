package com.icia.zws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/user/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/view_chat1")		
	public ModelAndView viewChat1() {
		return new ModelAndView("view_chat1");
	}

	@GetMapping("/view_chat2")
	public ModelAndView viewChat2() {
		return new ModelAndView("view_chat2");
	}
}
