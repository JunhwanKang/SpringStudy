package com.cdj.sboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
	private Integer ano;
	private Integer bno;
	private String originalFileName;
	private String saveFileName;
	private String writer;
	private Long length;
	private Boolean isImage;	
}
