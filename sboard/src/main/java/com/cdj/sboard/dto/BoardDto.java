package com.cdj.sboard.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.cdj.sboard.entity.Attachment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDto {

	@Data
	public static class Write{
		@NotBlank
		private String title;
		private String content;
	}
	
	@Data
	public static class ListView{
		private int bno;
		private String title;
		private String writer;
		private String writeTimeString;
		private int readCnt;
		private int attachmentCnt;
		private int commentCnt;
	}
	
	@Data
	public static class Read {
		private int bno;
		private String title;
		private String content;
		private String writer;
		private String writeTimeString;
		private int readCnt;
		private int attachmentCnt;
		private int commentCnt;
		private int deleteCommentCnt;
		private int goodCnt;
		private int badCnt;
		private Boolean isWriter;		
		private List<Attachment> attachments;
		private List<CommentDto.Read> comments;
	}
}
