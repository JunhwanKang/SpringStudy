package com.cdj.zmarket.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdj.zmarket.service.rest.ProductServiceRest;

@Controller
public class ProductControllerRest {
	@Autowired
	private ProductServiceRest service;
	
	@PostMapping("/product/sell")
	public ResponseEntity<?> sell(@RequestParam MultipartFile img) {
		service.register(img);
		return ResponseEntity.ok(null); 
	}
}
