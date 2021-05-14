package com.cdj.zmarket.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public ModelAndView main() {
		return new ModelAndView("main").addObject("viewpage", "product/list.jsp");
	}


}
