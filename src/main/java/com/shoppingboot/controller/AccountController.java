package com.shoppingboot.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.UserEntity;
import com.shoppingboot.model.UserPassEntity;
import com.shoppingboot.model.UserPassModel;
import com.shoppingboot.repository.UserRepository;
import com.shoppingboot.service.AccountHService;

@Controller 
public class AccountController {

//	@Autowired
//	AccountServiceImpl accountService;
	
	@Autowired
	AccountHService accountHService;
	
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value="/user/password")
	public String password(Model model) {
		model.addAttribute("user", new UserPassEntity());
		return "user/password";
	}

	@RequestMapping(value="/user/password", method=RequestMethod.POST)
	public ModelAndView postPassword(@Valid  @ModelAttribute("user") UserPassModel user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("user/password");
		if(bindingResult.hasErrors()) {
//			System.out.println(bindingResult);
			return new ModelAndView("user/password");
		} 
		
		String status = accountHService.updatePassword(user);
		model.addObject("message", status);
//		System.out.println(status);
		return model;	
	}

	@RequestMapping(value="/user/profile")
	public ModelAndView profile() {
		ModelAndView model = new ModelAndView("user/profile");
		
//		model.addObject("user", userRepo.getCurrentUser("test"));
		model.addObject("user", accountHService.getCurrentUser());

		return model;
	}

	@RequestMapping(value="/user/profile", method=RequestMethod.POST)
	public ModelAndView postProfile(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("user/profile");
//		model.addObject("user", accountHService.update(user));

		if(bindingResult.hasErrors()) {
			return new ModelAndView("user/profile");
		} 

		String status = accountHService.update(user);
			model.addObject("message", status);
			return model;			
		}

	}
