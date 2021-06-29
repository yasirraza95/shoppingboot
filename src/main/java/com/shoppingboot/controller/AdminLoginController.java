package com.shoppingboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingboot.model.User;

@Controller 
public class AdminLoginController {

	@RequestMapping("/admin/login")
	public String admin(@ModelAttribute("user") User user) {
		return "admin/adminLogin";
	}
}
