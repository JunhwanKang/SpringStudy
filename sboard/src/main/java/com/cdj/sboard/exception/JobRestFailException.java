package com.cdj.sboard.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JobRestFailException extends RuntimeException{
	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
