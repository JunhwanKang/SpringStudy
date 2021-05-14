package com.cdj.sboard.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdj.sboard.dto.UserDto;
import com.cdj.sboard.service.BoardService;
import com.cdj.sboard.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/user/join")
	
	public ModelAndView join() {
		return new ModelAndView("main").addObject("viewname", "user/join.jsp");
	}
	
	@GetMapping("/user/login")
	public ModelAndView login() {
		return new ModelAndView("main").addObject("viewname", "user/login.jsp");
	}
	
	@GetMapping("/user/join_check")
	public ModelAndView joinCheck(@RequestParam String checkCode) {
		service.joinCheck(checkCode);
		return new ModelAndView("main").addObject("viewname", "user/login.jsp");
	}
	
	@GetMapping("/user/findid")
	public ModelAndView findUser() {
		return new ModelAndView("main").addObject("viewname", "user/findid.jsp");
	}
	
	@GetMapping("/user/findpwd")
	public ModelAndView findPwd() {
		return new ModelAndView("main").addObject("viewname", "user/findpwd.jsp");
	}

	@PostMapping("/user/reset_pwd")
	public ModelAndView reset_pwd(@ModelAttribute @Valid UserDto.ResetPassword dto, BindingResult bindingResult, RedirectAttributes ra) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		service.resetPwd(dto);
		ra.addFlashAttribute("msg", "임시 비밀번호를 이메일로 전송했습니다");
		return new ModelAndView("redirect:/user/login");
	}
	
	@GetMapping("/user/info")
	public ModelAndView info(Principal principal) {
		String username = principal.getName();
		return new ModelAndView("main").addObject("user", service.info(username)).addObject("viewname", "user/info.jsp");
	}
		
	@PostMapping("/user/resign")
	public ModelAndView resign(SecurityContextLogoutHandler handler, HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) {
		service.resign(authentication.getName());
		handler.logout(request, response, authentication);
		return new ModelAndView("redirect:/");
	}
	
}
