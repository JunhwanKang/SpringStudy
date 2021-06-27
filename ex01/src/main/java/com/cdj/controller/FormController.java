package com.cdj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdj.domain.FormVo;

@Controller
public class FormController {
	
	@RequestMapping(value="/save")
	public String memberSave(@ModelAttribute FormVo member, BindingResult result) {
		System.out.println("이름: "+ member.getName());
		System.out.println("아이디: "+ member.getId());
		System.out.println("비밀번호: "+ member.getPwd());
		System.out.println("이메일: "+ member.getEmail());
		
		return "redirect:/input";
	}
	
	@RequestMapping("/input")
	public ModelAndView memberInput() {
		return new ModelAndView("testForm","formVo", new FormVo());
	}
}
