package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.User;
import com.shoppingboot.service.UserHibernateService;

@Controller 
public class AdminProviderController {

//	@Autowired
//	UserServiceImpl userService;
	
	@Autowired
	UserHibernateService userHService;

	@RequestMapping("/admin/addprovider")
	public ModelAndView addProvider() {
        ModelAndView model = new ModelAndView("admin/addProvider");
        model.addObject("user", new User());
        return model;
    }

	@RequestMapping("/admin/editprovider")
	public ModelAndView editProvider(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView("admin/editProvider");
//		model.addObject("user", userService.getSingleUser(id));
		model.addObject("user", userHService.getSingleUser(id));
		return model;
	}

	@RequestMapping("/admin/adminproviders")
	public ModelAndView adminProviders() {
		ModelAndView model = new ModelAndView("admin/providers");
//		model.addObject("users", userService.getUsers("provider"));
		model.addObject("users", userHService.getUsers("provider"));
		return model;
	}
	
}
