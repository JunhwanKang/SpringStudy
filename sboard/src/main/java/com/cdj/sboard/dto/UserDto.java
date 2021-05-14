package com.cdj.sboard.dto;

import java.time.LocalDate;



import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
	@Data
	public static class Join{
		private String username;
		private String password;
		private String irum;
		private String email;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private LocalDate birthday;
	}
	
	@Data
	public static class ResetPassword {
		private String username;
		private String email;
	}
}
