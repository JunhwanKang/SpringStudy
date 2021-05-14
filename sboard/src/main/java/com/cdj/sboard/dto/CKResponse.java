package com.cdj.sboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CKResponse {
	private int uploaded;
	private String fileName;
	private String url;
}
