package com.cdj.sboard.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdj.sboard.exception.JobMvcFailException;
import com.cdj.sboard.exception.JobRestFailException;

@ControllerAdvice
public class SboardAdvice {
	@ExceptionHandler(JobMvcFailException.class)
	public ModelAndView JMFHandler(JobMvcFailException e, RedirectAttributes ra){
		ra.addFlashAttribute("viewname", e.getMessage());
		return new ModelAndView("redirect:/system/error");
	}
	
	@ExceptionHandler(JobRestFailException.class)
	public ResponseEntity<?> JRFHandler(JobRestFailException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
