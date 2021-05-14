package com.cdj.zmarket.controller.mvc;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Secured("ROLE_USER")
	@GetMapping("/product/sell")
	public ModelAndView sell() {
		return new ModelAndView("main").addObject("viewpage", "product/productSell.jsp");
	}
	
}
