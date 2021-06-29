package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.User;
import com.shoppingboot.service.ProductHibernateService;
import com.shoppingboot.service.ProductServiceImpl;

@Controller 
public class ProductsController {

//	@Autowired
//	ProductServiceImpl productService;
	
	@Autowired
	ProductHibernateService prodHService;
	
	@RequestMapping(value="/user/products")
    public ModelAndView products(@RequestParam(value="prod", required=true) String parameter, User employee) {
        ModelAndView model = new ModelAndView("user/products");
//        model.addObject("products", productService.getProducts(parameter));
        model.addObject("products", prodHService.getCategoryProducts(parameter));
        return model;
    }
}
