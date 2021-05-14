package com.cdj.zmarket.dao;

import com.cdj.zmarket.entity.User;

public interface UserDao {
	public int insert(User user);
	
	public User findById(String username);

	public User findByEmail(String email);

	public User findByCheckCode(String checkCode);

	public int update(String username);
}
