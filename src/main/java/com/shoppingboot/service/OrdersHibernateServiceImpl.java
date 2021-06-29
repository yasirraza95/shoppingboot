package com.shoppingboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.CartModelInterface;
import com.shoppingboot.model.OrderDetailEntity;
import com.shoppingboot.model.OrderModelInterface;
import com.shoppingboot.model.OrdersEntity;
import com.shoppingboot.model.ShipUser;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;
import com.shoppingboot.repository.CartRepository;
import com.shoppingboot.repository.OrderDetailRepository;
import com.shoppingboot.repository.OrdersRepository;
import com.shoppingboot.repository.ProductRepository;

@Service
@Transactional
public class OrdersHibernateServiceImpl implements OrdersHibernateService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	CartHibernateService cartHService;
	
	private Authentication auth = null;

	
	@Override
	public List<OrdersEntity> getAdminOrders(String type) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();

		List<OrdersEntity> orderList = null;
		if(type == "all") {
//			orderList = ordersRepository.getAdminOrders();
			orderList = ordersRepository.findAll();
		} else {
//			orderList = ordersRepository.getAdminOrdersByType(type);
			orderList = ordersRepository.findAllByOrderStatus(type);
		}
		return orderList;
	}

	@Override
	public ResultType placeOrder(OrdersEntity user) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType status = ResultType.NOTHING;
//		status = ordersRepository.insertOrder(tgtUser.getId(), user.getName(), user.getMobile(), user.getEmail(), user.getAddress());
		
		OrdersEntity orderEntity = new OrdersEntity(tgtUser.getId(), user.getName(), user.getMobile(), user.getEmail(), user.getAddress(), "Pending");
		orderEntity = ordersRepository.save(orderEntity);
		long orderId = orderEntity.getId();

		long detail;
		String orderNumber;
		
		if(orderId > 0) {
			//order no
			orderNumber = String.format("%04d", orderId);
			
			//get cart
			List<CartModelInterface> cartData = cartHService.getCart();
//			System.out.println("data="+cartData.get(0).getPrice());
			
			int prodId;
			String prodCharges;
			int prodQuantity;
			int totalCharges = 0;
			
			//insert to order detail
			for(int i=0; i<cartData.size(); i++) {
				prodId = cartData.get(i).getProdId();
				prodCharges = cartData.get(i).getPrice();
				prodQuantity = cartData.get(i).getQuantity();

				detail = insertDetail(orderId, prodId, prodCharges, prodQuantity);
				
				totalCharges = totalCharges + (Integer.parseInt(prodCharges) * prodQuantity);
				
				if(detail > 0) {
				// update order
					
					updateOrder(orderNumber, totalCharges, orderId);
					
				// remove cart
					int removeRes = removeCart();
					if(removeRes > 0) {
						status = ResultType.UPDATED;
					}					
				}
			}
		}
		
		return status;
	}

	@Override
	public List<OrdersEntity> getUserOrders() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();

//		List<OrdersEntity> orderList = ordersRepository.getUserOrders(tgtUser.getId());
		List<OrdersEntity> orderList = ordersRepository.findAllById(tgtUser.getId());
		return orderList;
	}

	@Override
	public OrderModelInterface getOrderDetail(int orderId) {
		OrderModelInterface orderList = orderDetailRepository.getOrderDetail(orderId);
//		OrderDetailEntity orderList = orderDetailRepository.findOneById(orderId);
//		System.out.println("list = "+orderList.getOrdNo());
		return orderList;
	}

	@Override
	public List<OrderModelInterface> getOrderProducts(int orderId) {
		List<OrderModelInterface> orderList = orderDetailRepository.getOrderProducts(orderId);
//		System.out.println("list = "+orderList);
		return orderList;
	}

	@Override
	public long insertOrder(int id, String name, String mobile, String email, String address) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
//		int response = 0;
//		response = ordersRepository.insertOrder(tgtUser.getId(), name, mobile, email, address);

		OrdersEntity orderEntity = new OrdersEntity(tgtUser.getId(), name, mobile, email, address, "Pending");
		orderEntity = ordersRepository.save(orderEntity);		
//		System.out.println("data="+orderEntity);
		return orderEntity.getId();
	}

	@Override
	public int removeCart() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
//		return cartRepository.removeCart(tgtUser.getId());
		cartRepository.deleteByUserId(tgtUser.getId());
		return 1;
	}

	@Override
	public long insertDetail(long orderId, int prodId, String prodCharges, int quantity) {
		OrderDetailEntity detailEntity = new OrderDetailEntity();
		detailEntity.setOrderId(Integer.parseInt(""+orderId));
		detailEntity.setProdId(prodId);
		detailEntity.setCharges(Integer.parseInt(prodCharges));
		detailEntity.setQuantity(quantity);
		detailEntity = orderDetailRepository.save(detailEntity);
		return detailEntity.getId();
//		return ordersRepository.insertDetail(orderId, prodId, prodCharges, quantity);
	}

	@Override
	public int updateOrder(String orderNo, int totalCharges, long id) {
//		return ordersRepository.updateOrder(orderNo, totalCharges, id);
		OrdersEntity orderEntity = new OrdersEntity();
		orderEntity.setId(id);
		orderEntity.setOrdNo(orderNo);
		orderEntity.setCharges(""+totalCharges);
		orderEntity = ordersRepository.save(orderEntity);
		return Integer.parseInt(""+orderEntity .getId());
	}

}
