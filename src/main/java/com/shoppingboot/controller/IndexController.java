package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.User;
import com.shoppingboot.repository.CartRepository;
import com.shoppingboot.repository.ProductRepository;
import com.shoppingboot.service.ProductHibernateService;
import com.shoppingboot.service.ProductServiceImpl;

@Controller
public class IndexController {
//	@Autowired
//	ProductServiceImpl productService;
	
	@Autowired
	ProductHibernateService prodHService;
	
	@Autowired
	CartRepository cartRepo;
	
	private Authentication auth = null;
	
	@RequestMapping("/user/")
    public ModelAndView listEmployees(User employee) {

		auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("auth:"+auth.getAuthorities());
//		System.out.println("counter="+cartRepo.countByUserId(18));
		System.out.println("counter="+cartRepo.count());
		ModelAndView model = new ModelAndView("user/index");
//        model.addObject("products", prodRepo.findAll());
        model.addObject("products", prodHService.getTopProducts());
        return model;
    }
}
