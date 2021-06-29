package com.shoppingboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppingboot.model.Message;

@Controller 
public class ContactController {
	
	@RequestMapping("/user/contact")
	public String contact(Model model) {
		model.addAttribute("user", new Message());
		return "user/contactus";
	}
	
	@RequestMapping(value="/user/contact", method=RequestMethod.POST)
	public String contact(@ModelAttribute("user") Message message) {
		return "user/contactus";
	}
}
