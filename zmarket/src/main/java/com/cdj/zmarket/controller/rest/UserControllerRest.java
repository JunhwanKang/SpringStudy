package com.cdj.zmarket.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdj.zmarket.dao.UserDao;
import com.cdj.zmarket.entity.User;
import com.cdj.zmarket.service.rest.UserRestService;

@RestController
public class UserControllerRest {
	@Autowired
	private UserRestService service;
	
	@GetMapping("/users/usernameCheck")
	public ResponseEntity<?> usernameCheck(@RequestParam String username) {
		service.usernameCheck(username);
		
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/users/emailCheck")
	public ResponseEntity<?> emailCheck(@RequestParam String email) {
		service.emailCheck(email);
		return ResponseEntity.ok(null);
	}
	
}
