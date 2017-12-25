package com.osmsh.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	
	@RequestMapping(value =  "/", method = RequestMethod.GET)
	public String gethome(ModelMap model) {	
		return "index";
	}
	
	
	@RequestMapping(value =  "/about", method = RequestMethod.GET)
	public String getAbout(ModelMap model) {	
		return "about";
	}
	@RequestMapping(value =  "/blog", method = RequestMethod.GET)
	public String getBlog(ModelMap model) {	
		return "blog";
	}
	@RequestMapping(value =  "/contact", method = RequestMethod.GET)
	public String getContact(ModelMap model) {	
		return "contact";
	}
	@RequestMapping(value =  "/portfolio", method = RequestMethod.GET)
	public String getPortfolio(ModelMap model) {	
		return "portfolio";
	}
	@RequestMapping(value =  "/services", method = RequestMethod.GET)
	public String getServices(ModelMap model) {	
		return "services";
	}
	
}
