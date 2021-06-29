package com.shoppingboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.shoppingboot.model.OrderDetailEntity;
import com.shoppingboot.model.OrderModelInterface;

public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Long> {

	@Query(value="select p.id, p.name as prodName, p.image, od.prod_charges as charges, od.quantity from products p inner join order_detail od on p.id = od.prod_id where p.id in (select prod_id from order_detail where orders_id = ?1 ) group\r\n" + 
			"by p.id", nativeQuery=true)
	public List<OrderModelInterface> getOrderProducts(int ordersId);
	
	@Query(value="select order_number from orders where orders.id = ?1", nativeQuery=true)
	public OrderModelInterface getOrderDetail(int ordersId);
	
//	public OrderModelInterface findById(int id);
}
