package com.cdj.zmarket.service.mvc;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdj.zmarket.dao.UserDao;
import com.cdj.zmarket.dto.UserDto;
import com.cdj.zmarket.entity.User;
import com.cdj.zmarket.util.MailUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailUtil mailUtil;
	
	public void join(UserDto.member userDto) {
		User user = modelMapper.map(userDto, User.class);
		
		String encPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		
		String checkCode = RandomStringUtils.randomAlphanumeric(20);
		user.setCheckCode(checkCode);
		
		userDao.insert(user);
		mailUtil.joinCheckMail("admin@cdj.com", user.getEmail(), checkCode);
		
		
	}

	public void joinCheck(String checkCode) {
		User user = userDao.findByCheckCode(checkCode);
		System.out.println("===========================");
		System.out.println(user.getUsername());
		System.out.println("===========================");
		userDao.update(user.getUsername());
	}

}
