package com.cdj.sboard.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdj.sboard.dao.UserDao;
import com.cdj.sboard.dto.UserDto.ResetPassword;
import com.cdj.sboard.entity.Board;
import com.cdj.sboard.entity.User;
import com.cdj.sboard.exception.JobMvcFailException;
import com.cdj.sboard.exception.MvcUserNotFoundException;
import com.cdj.sboard.util.MailUtil;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailUtil mailUtil;
	
	public void joinCheck(String checkCode) {
		User result = dao.findByCheckCode(checkCode);
		if(result!=null) {
			User user = User.builder().enabled(true).checkCode("1").username(result.getUsername()).build();
			dao.update(user);
		} else {
			throw new JobMvcFailException("�ùٸ��� ���� üũ�ڵ��Դϴ�.");
		}
	}

	public void resetPwd(ResetPassword dto) {
		User result = dao.findById(dto.getUsername());
		if(result==null)
			throw new MvcUserNotFoundException();
		if(result.getEmail().equals(dto.getEmail())==false)
			throw new JobMvcFailException("�̸����� Ȯ���� �� �����ϴ�.");
		
		 String password = RandomStringUtils.randomAlphanumeric(20);
		 String encodedPassword = passwordEncoder.encode(password);
		 
		 User user = User.builder().username(dto.getUsername()).password(encodedPassword).build();
		 dao.update(User.builder().username(dto.getUsername()).password(encodedPassword).build());
		 mailUtil.sendResetPasswordMail("admin@sboard.com", dto.getEmail(), password);
	}

	public User info(String username) {
		User user = dao.findById(username);
		user.setProfile("http://localhost:8081/profile/"+user.getProfile());
		return user;
	}

	public void resign(String username) {
		dao.deleteById(username);
	}


	 
	
	
}
