package com.cdj.test001;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "test002";
	}
	
	@RequestMapping(value="view")
	public String view() {
		return "view";
	}
	
	@RequestMapping(value="pdfView1")
	public String pdfView1() {
		return "pdfView1";
	}
	
	@RequestMapping(value="pdfView2")
	public String pdfView2() {
		return "pdfView2";
	}
	
	@RequestMapping(value="pdfView3")
	public String pdfView3() {
		return "pdfView3";
	}
}
