package com.shoppingboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;
import com.shoppingboot.model.CartModelInterface;
import com.shoppingboot.model.OrdersEntity;
import com.shoppingboot.model.ShipUser;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;
import com.shoppingboot.service.CartHibernateService;
import com.shoppingboot.service.CartServiceImpl;
import com.shoppingboot.service.OrderServiceImpl;
import com.shoppingboot.service.OrdersHibernateService;

@Controller 
public class CheckoutController {
	
//	@Autowired
//	OrderServiceImpl orderService;
	
	@Autowired
	OrdersHibernateService orderHService;
	
//	@Autowired
//	CartServiceImpl cartService;
	
	@Autowired
	CartHibernateService cartHService;
	
	private Authentication auth = null;

	
	@RequestMapping("/user/checkout")
	public ModelAndView checkout(@ModelAttribute("user") ShipUser user) {
		ModelAndView model = new ModelAndView("user/checkout");
//		auth = SecurityContextHolder.getContext().getAuthentication();
//		model.addObject("cartData", cartService.getCart());
//		return model;
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
//		List<Cart> cartList = cartService.getCartCounter(tgtUser.getId());
		int counter = cartHService.getCartCounter(tgtUser.getId());
//		int counter = cartList.size();
		
		if(counter > 0) {
			model.addObject("cartData", cartHService.getCart());
			return model;	
		} else {
			return new ModelAndView("redirect:/user/cart");
		}
		
	}
	 
	@RequestMapping(value="/user/placeorder", method=RequestMethod.POST)	
	public String placeOrder(@ModelAttribute("user") OrdersEntity user) {
		
		auth = SecurityContextHolder.getContext().getAuthentication();

		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
//		user.setUserId(Integer.parseInt("18"));
		
//		ResultType response = orderService.placeOrder(user);
//System.out.println("user object="+user);		
		ResultType response = orderHService.placeOrder(user);
		
		
		String redirection;
		if(response.toString() == "UPDATED") {
			redirection = "redirect:/user/";
		} else {
			redirection = "redirect:cart";
		}
		return redirection;
	}
}
