package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.service.OrdersHibernateService;

@Controller 
public class AdminOrdersController {

//	@Autowired
//	OrderServiceImpl orderService;
	
	@Autowired
	OrdersHibernateService orderHService;

	@RequestMapping("/admin/adminorders")
	public ModelAndView adminOrders(@RequestParam("type") String type) {
		ModelAndView model = new ModelAndView("admin/orders");
//		model.addObject("orders", orderService.getAdminOrders(type));
		model.addObject("orders", orderHService.getAdminOrders(type));
		return model;
	}
}
