package com.shoppingboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.service.OrderServiceImpl;
import com.shoppingboot.service.OrdersHibernateService;

@Controller 
public class OrdersController {
	
	@Autowired
	OrderServiceImpl orderService;

	@Autowired
	OrdersHibernateService orderHibernateService;

	
	@RequestMapping(value="/user/orders")
	public ModelAndView orders() {
        ModelAndView model = new ModelAndView("user/orders");
        
//        model.addObject("orders", orderService.getUserOrders());
        model.addObject("orders", orderHibernateService.getUserOrders());
        
        return model;
    }
	
	@RequestMapping(value="/user/detail")
	public ModelAndView orderDetail(@RequestParam("id") int id) {
        ModelAndView model = new ModelAndView("user/orderDetail");
//        model.addObject("orderDetail", orderService.getOrderDetail());
//        model.addObject("orderProducts", orderService.getOrderProducts());
//        System.out.println(""+id);
        model.addObject("orderDetail", orderHibernateService.getOrderDetail(id));
        model.addObject("orderProducts", orderHibernateService.getOrderProducts(id));
        
        return model;
    }
}
