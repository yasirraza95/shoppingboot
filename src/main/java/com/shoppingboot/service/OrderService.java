package com.shoppingboot.service;

import java.util.List;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Orders;
import com.shoppingboot.model.User;

public interface OrderService {

	public List<Orders> getAdminOrders(String type);
	public ResultType placeOrder(User user);
	public List<Orders> getUserOrders();
	public Orders getOrderDetail();
	public List<Orders> getOrderProducts();
	
}
