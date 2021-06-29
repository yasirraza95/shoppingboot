package com.shoppingboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class AboutController {

	@GetMapping("/user/about")
	public String about() {
		return "user/about";
	}
}