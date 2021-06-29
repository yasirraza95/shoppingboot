package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.User;
import com.shoppingboot.service.AccountHService;

@Controller 
public class AdminAccountController {

//	@Autowired
//	AccountServiceImpl accountService;
	
	@Autowired
	AccountHService accountHService;
	
	@RequestMapping("/admin/adminpassword")
	public String adminPassword(@ModelAttribute("user") User user) {
		return "admin/password";
	}
	
	
	@RequestMapping("/admin/adminprofile")
	public ModelAndView adminProfile() {
        ModelAndView model = new ModelAndView("admin/profile");

//        model.addObject("user", accountService.getAdmin());
        model.addObject("user", accountHService.getAdmin());
        return model;
    }
}
