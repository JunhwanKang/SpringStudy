package com.cdj.zmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class Mail {
	private String from;
	private String to;
	private String subject;
	private String text;
}
