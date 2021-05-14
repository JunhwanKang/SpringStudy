package com.cdj.zmarket.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdj.zmarket.dao.UserDao;

@Service
public class UserRestService {
	@Autowired
	private UserDao userDao;
	
	public void usernameCheck(String username) {
		userDao.findById(username);
	}

	public void emailCheck(String email) {
		userDao.findByEmail(email);
	}

	
}
