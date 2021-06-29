package com.shoppingboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class LoginController {
	
	private Authentication auth = null;
	
	@RequestMapping(value="/user/login")
	public ModelAndView login() {

		auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth:"+auth);
		if(auth.getPrincipal() != "anonymousUser") {
			return new ModelAndView("redirect:/user/");
		} else {
			return new ModelAndView("user/login");
		}					
	}
	
}
