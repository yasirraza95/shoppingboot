package com.shoppingboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shoppingboot.dao.OrdersDAO;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Orders;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;

@Component
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrdersDAO ordersDAO;
	
	public List<Orders> getAdminOrders(String type) {
		List<Orders> orders = ordersDAO.getOrders(type);
		return orders;
	}
	
	public ResultType placeOrder(User user) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType response = ordersDAO.placeOrder(tgtUser.getId(), user.getName(), user.getMobile(), user.getEmail(), user.getAddress());
	
		return response;
	}
	
	public List<Orders> getUserOrders() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		List<Orders> orderList = ordersDAO.getUserOrders(tgtUser.getId());
		return orderList;
	}
	
	public Orders getOrderDetail() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		
		Orders orderDetail = ordersDAO.getOrderDetail(tgtUser.getId());

		return orderDetail;
	}
	
	public List<Orders> getOrderProducts() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		List<Orders> orderProducts = ordersDAO.getOrderProducts(tgtUser.getId());
		return orderProducts;
	}

}
