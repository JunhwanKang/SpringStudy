package com.cdj.sboard.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface AuthorityDao {
	@Insert("insert into authorities(username, authority) values(#{username}, #{authority})")
	public int insert(@Param("username") String username, @Param("authority") String authority);
}
