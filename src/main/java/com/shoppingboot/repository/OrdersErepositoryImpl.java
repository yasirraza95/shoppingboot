package com.shoppingboot.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Orders;

public class OrdersErepositoryImpl implements OrdersErepository {

	private Calendar calendar;
	private Date currentTime;
	public long time;
	int totalPrice = 0;
	Long ordId;
	int prodId = 0;
	int prodPrice = 0;
	int quantity = 0;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}

	
	@Override
	public ResultType placeOrder(Long userId, String name, String mobile, String email, String address) {
		ResultType status = ResultType.NOTHING;

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		calendar = Calendar.getInstance();
		currentTime = calendar.getTime();
		time = currentTime.getTime();

		String chkStmt = "select count(*) from cart where user_id = ?";

		int count = (int) em.createNativeQuery(chkStmt).setParameter(1, userId).getSingleResult();
//
//			if(count > 0) {
//				GeneratedKeyHolder holder = new GeneratedKeyHolder();
//				jdbcTemplate.update(new PreparedStatementCreator() {
//
//					@Override
//					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//						PreparedStatement insStmt = con.
//								prepareStatement("insert into orders set user_id = ?, shipper_name = ?,"
//										+ "shipper_mobile = ?, shipper_email = ?, shipper_address = ?", Statement.RETURN_GENERATED_KEYS);
//						insStmt.setLong(1, userId);
//						insStmt.setString(2, name);
//						insStmt.setString(3, mobile);
//						insStmt.setString(4, email);
//						insStmt.setString(5, address);
//						return insStmt;
//					}
//					
//				}, holder);
//				
//				long lastInsertedId = holder.getKey().longValue();
//				
//					
//					if(lastInsertedId > 0) {
//						ordId = lastInsertedId;
//						//loop through order detail from cart
//						String cartStmt = "select * from cart where user_id = ?";
//						jdbcTemplate.query(cartStmt, new Object[] {userId}, cartRes -> {
//							while(cartRes.next()) {
//								prodId = cartRes.getInt("prod_id");
//								quantity = cartRes.getInt("quantity");
//
//								String prodStmt = "select * from products where id = ?";
//								jdbcTemplate.query(prodStmt, new Object[] {prodId}, prodRes -> {
//									while(prodRes.next()) {
//										prodPrice = prodRes.getInt("price");
//										totalPrice = totalPrice + (prodPrice * quantity);
//									}
//									return;
//								});
//
//								String ordDetStmt = "insert into order_detail set orders_id = ?, prod_id = ?, "
//										+ "prod_charges = ?, quantity = ?";
//								jdbcTemplate.update(ordDetStmt, new Object[] {ordId, prodId, prodPrice, quantity});
//								
//							}
//							return;
//						});
//						
//
//						String orderNumber = String.format("%04d", ordId);
//						String updtStmt = "update orders set order_number = ?, total_charges = ? where id = ?";
//						int updatedRows = jdbcTemplate.update(updtStmt, new Object[] {orderNumber, totalPrice, ordId});
//						
//						if(updatedRows > 0) {
//							String cartDelStmt = "delete from cart where user_id = ?";
//							int deletedRows = jdbcTemplate.update(cartDelStmt, new Object[] {userId});
//							
//							if(deletedRows > 0) {
//								status = ResultType.UPDATED;
//							}
//						}
//
//					}
//
//			}
			
		return status;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrders(String type) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		List<Orders> ordersList = new ArrayList<>();

		String statement = "";	
			if(type.equals("all")) {
				statement = "select * from orders";
				ordersList = em.createNativeQuery(statement).getResultList();
			} else {
				statement = "select * from orders where order_status = ?";
				ordersList = em.createNativeQuery(statement).setParameter(1, type).getResultList();
			}

		return ordersList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getUserOrders(Long userId) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		List<Orders> arrayList = new ArrayList<>();

		String statement = "select * from orders where user_id = ?";
		
		arrayList = em.createNativeQuery(statement).setParameter(1, userId).getResultList();
		return arrayList;
	}

	@Override
	public Orders getOrderDetail(Long orderId) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		Orders orders = new Orders();
		
		String statement = "select count(*) from orders where id = ?";
		int count = (int) em.createNativeQuery(statement).setParameter(1, orderId).getSingleResult();

		boolean exist = false;	

		if(count > 0) {
			exist = true;
		}
			
		if(exist) {
			String orderDetailstatement = "select order_number, total_charges from orders where id = ?";
			orders = (Orders)em.createNativeQuery(orderDetailstatement).setParameter(1, orderId).getResultList();
		}
			
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrderProducts(Long orderId) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		List<Orders> arrayList = new ArrayList<>();
		
		String statement = "select count(*) from orders where id = ?";
		int count = (int) em.createNativeQuery(statement).setParameter(1, orderId).getSingleResult();

		boolean exist = false;	
		
		if(count > 0) {
			exist = true;
		}
		
		if(exist) {
			String orderDetailstatement = "select * from order_detail where orders_id =  ?";
			arrayList = em.createNativeQuery(orderDetailstatement).setParameter(1, orderId).getResultList();
		}
		
		return arrayList;
	}

	@Override
	public ResultType processOrders(String id, String orderStat) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		ResultType status = ResultType.NOTHING;
		
		String statement = "select count(*) from orders where id = ?";

		int count = (int) em.createNativeQuery(statement).setParameter(1, id).getSingleResult();

		if(count > 0) {
			String updtStmt = "update orders set order_status = ? where id = ?";
			int updatedRows = em.createNativeQuery(updtStmt).setParameter(1, orderStat).setParameter(2, id).executeUpdate();
			
			if(updatedRows > 0) {
				status = ResultType.UPDATED;
			} else {
				status = ResultType.NOTHING;
			}
		} else {
			status = ResultType.NOTHING;
		}
			
		return status;
	}

}
