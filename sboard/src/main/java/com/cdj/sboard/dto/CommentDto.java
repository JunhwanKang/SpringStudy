package com.cdj.sboard.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class CommentDto {
	@Data
	public static class Read {
		private Integer cno;
		private Integer bno;
		private String writer;
		private Boolean isWriter;
		private String content;
		private String writeTimeString;
		private String profile;
	}
}
