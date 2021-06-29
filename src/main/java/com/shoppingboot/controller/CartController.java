package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;
import com.shoppingboot.service.CartHibernateService;
import com.shoppingboot.service.CartServiceImpl;
import com.shoppingboot.service.OrdersHibernateService;

@Controller 
public class CartController {

//	@Autowired
//	CartServiceImpl cartService;
	
	@Autowired
	CartHibernateService cartHibernateService;
	
	@RequestMapping("/user/cart")
	public ModelAndView cart(@ModelAttribute("cart") Cart cart) {
		ModelAndView model = new ModelAndView("user/cart");
		
//		model.addObject("cartData", cartService.getCart());
		model.addObject("cartData", cartHibernateService.getCart());
		return model;
	}
	
	@RequestMapping(value="/user/cartProcess", method=RequestMethod.POST)
	@ResponseBody
	public String postCart(@RequestParam Integer id) {
		
//		ResultType response = cartService.addCart(id);
		ResultType response = cartHibernateService.addCart(id);
		return response.toString();
	}
	
	@RequestMapping(value="/user/updateCart", method=RequestMethod.POST)
	@ResponseBody
	public String updateCart(@RequestParam Integer prodId, @RequestParam String type) {
		
//		ResultType response = cartService.updateCart(prodId, type);
		ResultType response = cartHibernateService.updateCart(prodId, type);
		return response.toString();
	}
	
	@RequestMapping(value="/user/removeCart", method=RequestMethod.POST)
	@ResponseBody
	public String removeCart(@RequestParam("id") String prodId) {
		System.out.println("id="+prodId);
//		ResultType response = cartService.removeCart(prodId);
		ResultType response = cartHibernateService.removeCart(Integer.parseInt(prodId));
		return response.toString();
	}
}
