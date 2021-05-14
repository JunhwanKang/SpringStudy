package com.cdj.zmarket.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class User {
	private String username;
	private String password;
	private String email;
	private String name;
	private Boolean enable;  // ���� Ȱ��ȭ / ��Ȱ��ȭ
	private LocalDateTime joinDate;
	private Integer loginFailCnt;
	private String checkCode;
	
	private String naverid;
}
