package com.shoppingboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;
import com.shoppingboot.service.SignupHibernateService;
import com.shoppingboot.service.SignupService;
import com.shoppingboot.service.SignupServiceImpl;

@Controller 
public class SignupController {
	
	private Authentication auth = null;
	
//	@Autowired
//	@Qualifier("passwordValidator")
//	private Validator validator;
	
//	@Autowired
//	private SignupServiceImpl signupService;
	
	@Autowired
	private SignupHibernateService signupHService;

	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}
	
	@RequestMapping("/user/signup")
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView("user/signup");
		model.addObject("user", new User());
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getPrincipal() != "anonymousUser") {
			model = new ModelAndView("redirect:/user");
		} 
		return model;
	}
	
	@RequestMapping(value="/user/signup", method=RequestMethod.POST)
	public ModelAndView signup(@Valid @ModelAttribute("user")  User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("user/signup");
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("user/signup");
		} else {
//			String status = signupService.insert(user);
			String status = signupHService.insert(user);
			model.addObject("status", status);
			return model;			
		}
		
	}
}
