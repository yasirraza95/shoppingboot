package com.shoppingboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.OrdersEntity;
import com.shoppingboot.model.ProductEntity;

@Component
public interface OrdersRepository extends CrudRepository<OrdersEntity, Long> {

	@Query(value="select * from orders where user_id = ?1", nativeQuery=true)
	public List<OrdersEntity> getUserOrders(int userId);
	
	public List<OrdersEntity> findAllById(long userId);
	
	@Query(value="select * from orders", nativeQuery=true)
	public List<OrdersEntity> getAdminOrders();
	
	public List<OrdersEntity> findAll();
	
	@Query(value="select * from orders where order_status = ?1", nativeQuery=true)
	public List<OrdersEntity> getAdminOrdersByType(String type);
	
	public List<OrdersEntity> findAllByOrderStatus(String type);
	
	@Modifying
	@Transactional
	@Query(value="insert into orders set user_id = ?1, shipper_name = ?2, shipper_mobile = ?3,"
			+ " shipper_email = ?4, shipper_address = ?5", nativeQuery=true)
	public int insertOrder(int userId, String name, String mobile, String email, String address);
	
	@Modifying
	@Transactional
	@Query(value="insert into order_detail set orders_id = ?1, prod_id = ?2,"
			+ " prod_charges = ?3, quantity = ?4", nativeQuery=true)
	public int insertDetail(long orderId, int prodId, String prodCharges, int quantity);
	
	@Modifying
	@Transactional
	@Query(value="update orders set order_number = ?1, order_charges = ?2, total_charges = ?2 where id = ?3", nativeQuery=true)
	public int updateOrder(String orderNo, int totalCharges, long id);

	@Modifying
	@Transactional
	@Query(value="update orders set order_number = ?1 where id = ?2", nativeQuery=true)
	public ResultType updateOrderNo(String orderNo, int id);
}
