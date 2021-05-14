package com.cdj.sboard.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cdj.sboard.dao.AuthorityDao;
import com.cdj.sboard.dao.UserDao;
import com.cdj.sboard.dto.UserDto;
import com.cdj.sboard.dto.UserDto.Join;
import com.cdj.sboard.entity.Level;
import com.cdj.sboard.entity.User;
import com.cdj.sboard.exception.JobRestFailException;
import com.cdj.sboard.exception.RestUserNotFoundException;
import com.cdj.sboard.util.MailUtil;

@Service
public class UserRestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailUtil mailUtil;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthorityDao authorityuserDao;
	@Value("c:/upload/profile")
	private String profileFolder;
	
	public void idCheck(String username) {
		User user = userDao.findById(username);
		if(user!=null)
			throw new JobRestFailException("사용중인 아이디입니다.");
	}

	public void emailCheck(String email) {
		User user = userDao.findByEmail(email);
		if(user!=null)
			throw new JobRestFailException("사용중인 이메일입니다");
	}

	@Transactional
	public void join(UserDto.Join dto, MultipartFile profile) {
		User user = modelMapper.map(dto, User.class);
		
		if(profile!=null && profile.isEmpty()==false) {
			String profileName = user.getUsername() + ".jpg";
			File profileFile = new File(profileFolder, profileName);
			try {
				FileCopyUtils.copy(profile.getBytes(), profileFile);
				user.setProfile(profileName);
			} catch (IOException e) {
				user.setProfile("default.jpg");
				e.printStackTrace();
			}
		} else {
			user.setProfile("default.jpg");
		}
		 
		String checkCode = RandomStringUtils.randomAlphanumeric(20);
		System.out.println("chekCode는 :"+checkCode+"입니다.");
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword).setCheckCode(checkCode).setLevel(Level.NORMAL);
	
		userDao.insert(user);
		authorityuserDao.insert(user.getUsername(), "ROLE_USER");
		
		mailUtil.sendJoinCheckMail("admin@sboard.com", user.getEmail(), checkCode);
	}
	
	public String findId(String email) {
		User user = userDao.findByEmail(email);
		if(user==null) 
			throw new RestUserNotFoundException();
		return user.getUsername();
	}
	
	
	
	@Scheduled(cron="0 0 4 ? * THU")
	public void deleteUncheckUser() {
		userDao.deleteByCheckCodeIsNotNull();
	}

	public void passwordUpdate(String password, String username) {
		String newPassword = passwordEncoder.encode(password);
		userDao.update(User.builder().username(username).password(newPassword).build());
	}
}
