package com.cdj.zmarket.controller.mvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdj.zmarket.auth.SNSLogin;
import com.cdj.zmarket.auth.SnsValue;
import com.cdj.zmarket.dto.UserDto;
import com.cdj.zmarket.entity.User;
import com.cdj.zmarket.service.mvc.UserService;

import jdk.internal.org.jline.utils.Log;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private SnsValue naverSns;
	
	
	@GetMapping("/user/join")
	public ModelAndView join() {
		return new ModelAndView("/user/join");
	}
	
	@GetMapping("/user/login")
	public ModelAndView login(Model model) {
		SNSLogin snsLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		return new ModelAndView("/user/login");
	}
	
	@GetMapping("auth/{service}/callback")
	public ModelAndView snsLoginCallback(@PathVariable String service, Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception{
		
		SnsValue sns = null;
		if(StringUtils.equals("naver", service))
			sns = naverSns;
		
		// 1. code�� �̿��ؼ� access_token �ޱ�
		// 2. access_token�� �̿��ؼ� ����� ������ ���� ��������
		// 3. db�� �ش� ������ �����ϴ��� üũ (naverId �÷� �߰�)
		// 4. �����ϸ� �α���, �� ����� ���� ��������..
		SNSLogin snsLogin = new SNSLogin(sns);
		User snsUser = snsLogin.getUserProfile(code);
		System.out.println("====profile==== "+ snsUser);
 
		return new ModelAndView("main").addObject("viewpage", "product/list.jsp");
	}
	
	@PostMapping("/user/join")
	public ModelAndView join(@ModelAttribute UserDto.member userDto) {
		service.join(userDto);
		return new ModelAndView("/user/login");
	}
	
	@GetMapping("/user/join_check")
	public ModelAndView joinCheck(@RequestParam String checkCode) {
		service.joinCheck(checkCode);
		return new ModelAndView("/user/login");
	}
	
	@GetMapping("/users/findUsername")
	public ModelAndView findUsername() {
		return new ModelAndView("main").addObject("viewpage", "user/findUsername.jsp");
	}
	
	/*
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public void login(Model model) throws Exception{
		System.out.println("=================����===============");
		SNSLogin snsLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
	}
	*/
	

	
}
