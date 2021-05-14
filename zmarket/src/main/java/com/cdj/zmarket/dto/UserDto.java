package com.cdj.zmarket.dto;

import lombok.Data;

public class UserDto {
	@Data
	public static class member{
		private String username;
		private String password;
		private String email;
		private String name;
	}
}
