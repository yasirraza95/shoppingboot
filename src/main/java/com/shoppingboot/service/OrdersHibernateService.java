package com.shoppingboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.OrderDetailEntity;
import com.shoppingboot.model.OrderModelInterface;
import com.shoppingboot.model.Orders;
import com.shoppingboot.model.OrdersEntity;
import com.shoppingboot.model.ShipUser;
import com.shoppingboot.model.User;

@Component
public interface OrdersHibernateService {

	public List<OrdersEntity> getAdminOrders(String type);
	public ResultType placeOrder(OrdersEntity user);
	public List<OrdersEntity> getUserOrders();
	public OrderModelInterface getOrderDetail(int orderId);
	public List<OrderModelInterface> getOrderProducts(int orderId);
	public long insertOrder(int id, String name, String mobile, String email, String address);
	public long insertDetail(long orderId, int prodId, String prodCharges, int quantity);
	public int removeCart();
	public int updateOrder(String orderNo, int totalCharges, long orderId);
}
