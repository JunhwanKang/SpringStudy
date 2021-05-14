/*
		üũ�ڵ带 Ȯ�� -> üũ�ڵ� ����, enabled�� ����
		��й�ȣ ����
		�� ���� ���� : ��й�ȣ, �̸���, ����
		�α��� ���� Ƚ�� ����
		���� ����
		�۾� Ƚ�� ����
 */

package com.cdj.sboard.controller;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdj.sboard.dto.UserDto;
import com.cdj.sboard.service.UserRestService;

@RestController
public class UserRestController {
	@Autowired
	private UserRestService service;
	
	
	@GetMapping("/users/user/username")
	public ResponseEntity<?> idCheck(@RequestParam String username){
		service.idCheck(username);
		return ResponseEntity.ok(null);
	}
	@GetMapping("/users/user/email")
	public ResponseEntity<?> emailCheck(@RequestParam String email){
		service.emailCheck(email);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/users/member")
	public ResponseEntity<?> join(@ModelAttribute @Valid UserDto.Join dto, BindingResult bindingResult,
			@RequestParam MultipartFile profile) throws BindException{
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		service.join(dto, profile);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("users/username/email")
	public ResponseEntity<?> findId(@RequestParam String email){
		String username = service.findId(email);
		return ResponseEntity.ok(username);
	}
	
	@PostMapping("/users/newPassword")
	public ResponseEntity<?> newPassword(@RequestParam String password, Principal principal){
		System.out.println(password);
		service.passwordUpdate(password, principal.getName());
		return ResponseEntity.ok(null);
	}
}
