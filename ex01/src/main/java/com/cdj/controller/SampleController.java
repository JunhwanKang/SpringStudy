package com.cdj.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdj.domain.SampleDTO;
import com.cdj.domain.SampleDTOList;
import com.cdj.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dataFormat, false));
//	}
	
	@RequestMapping("")
	public void basic() {
		log.info("basic....");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.....");
	}
	
	// 간편하지만 제한적 (스프링 버전 4.3부터...)
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get....");
	}
	
	@RequestMapping(value = "/ex01", method = RequestMethod.GET)
	public String ex01(SampleDTO dto) {
		log.info("===="+dto);
		
		return "ex01";
	}
	
	@RequestMapping(value = "/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name:" + name);
		log.info("age: " + age);
		
		return "ex02";
	}
	
	@RequestMapping(value = "/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: " + ids);
		
		return "ex02List";
	}
	
	@RequestMapping(value = "/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	@RequestMapping(value = "/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		
		return "ex02Bean";
	}
	
	@RequestMapping(value = "/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		
		return "ex03";
	}
}
