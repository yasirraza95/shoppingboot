package com.shoppingboot.repository;

import java.util.List;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Orders;

public interface OrdersErepository {

	public ResultType placeOrder(Long userId, String name, String mobile, String email, String address);
	public List<Orders> getOrders(String type);
	public List<Orders> getUserOrders(Long userId);
	public Orders getOrderDetail(Long userId);
	public List<Orders> getOrderProducts(Long userId);
	public ResultType processOrders(String id, String orderStat);
}
