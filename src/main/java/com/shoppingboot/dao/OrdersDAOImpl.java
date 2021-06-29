package com.shoppingboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Orders;

public class OrdersDAOImpl implements OrdersDAO {
	private Calendar calendar;
	private Date currentTime;
	public long time;
	int totalPrice = 0;
	Long ordId;
	int prodId = 0;
	int prodPrice = 0;
	int quantity = 0;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	public ResultType placeOrder(Long userId, String name, String mobile, String email, String address) {

		ResultType status = ResultType.NOTHING;

		calendar = Calendar.getInstance();
		currentTime = calendar.getTime();
		time = currentTime.getTime();

		String chkStmt = "select count(*) from cart where user_id = ?";

		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {userId}, Integer.class);

			if(count > 0) {
				GeneratedKeyHolder holder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement insStmt = con.
								prepareStatement("insert into orders set user_id = ?, shipper_name = ?,"
										+ "shipper_mobile = ?, shipper_email = ?, shipper_address = ?", Statement.RETURN_GENERATED_KEYS);
						insStmt.setLong(1, userId);
						insStmt.setString(2, name);
						insStmt.setString(3, mobile);
						insStmt.setString(4, email);
						insStmt.setString(5, address);
						return insStmt;
					}
					
				}, holder);
				
				long lastInsertedId = holder.getKey().longValue();
				
					
					if(lastInsertedId > 0) {
						ordId = lastInsertedId;
						//loop through order detail from cart
						String cartStmt = "select * from cart where user_id = ?";
						jdbcTemplate.query(cartStmt, new Object[] {userId}, cartRes -> {
							while(cartRes.next()) {
								prodId = cartRes.getInt("prod_id");
								quantity = cartRes.getInt("quantity");

								String prodStmt = "select * from products where id = ?";
								jdbcTemplate.query(prodStmt, new Object[] {prodId}, prodRes -> {
									while(prodRes.next()) {
										prodPrice = prodRes.getInt("price");
										totalPrice = totalPrice + (prodPrice * quantity);
									}
									return;
								});

								String ordDetStmt = "insert into order_detail set orders_id = ?, prod_id = ?, "
										+ "prod_charges = ?, quantity = ?";
								jdbcTemplate.update(ordDetStmt, new Object[] {ordId, prodId, prodPrice, quantity});
								
							}
							return;
						});
						

						String orderNumber = String.format("%04d", ordId);
						String updtStmt = "update orders set order_number = ?, total_charges = ? where id = ?";
						int updatedRows = jdbcTemplate.update(updtStmt, new Object[] {orderNumber, totalPrice, ordId});
						
						if(updatedRows > 0) {
							String cartDelStmt = "delete from cart where user_id = ?";
							int deletedRows = jdbcTemplate.update(cartDelStmt, new Object[] {userId});
							
							if(deletedRows > 0) {
								status = ResultType.UPDATED;
							}
						}

					}

//				}
			}
			

		return status;
	}

	public List<Orders> getOrders(String type) {
		
		List<Orders> arrayList = new ArrayList<>();
		List<Orders> ordersList = new ArrayList<>();

		String statement = "";	
			if(type.equals("all")) {
				statement = "select * from orders";
				
				ordersList = jdbcTemplate.query(statement, rs -> {
					while(rs.next()) {
						Orders orders = new Orders();

						orders.setId(Integer.parseInt(rs.getString("id")));
						orders.setOrdNo(rs.getString("order_number"));
						orders.setCharges(rs.getString("total_charges"));
						orders.setStatus(rs.getString("order_status"));
						orders.setShipName(rs.getString("shipper_name"));
						orders.setShipMobile(rs.getString("shipper_mobile"));
						orders.setShipAddress(rs.getString("shipper_address"));
						orders.setDate(rs.getString("date"));

						arrayList.add(orders);
					}
					return arrayList;
				});
				
			} else {
				statement = "select * from orders where order_status = ?";
				
				ordersList = jdbcTemplate.query(statement, new Object[] {type},  rs -> {
					while(rs.next()) {
						Orders orders = new Orders();

						orders.setId(Integer.parseInt(rs.getString("id")));
						orders.setOrdNo(rs.getString("order_number"));
						orders.setCharges(rs.getString("total_charges"));
						orders.setStatus(rs.getString("order_status"));
						orders.setShipName(rs.getString("shipper_name"));
						orders.setShipMobile(rs.getString("shipper_mobile"));
						orders.setShipAddress(rs.getString("shipper_address"));
						orders.setDate(rs.getString("date"));

						arrayList.add(orders);
					}
					return arrayList;
				});
			}


		return ordersList;
	}

	public List<Orders> getUserOrders(Long userId) {
		
		List<Orders> arrayList = new ArrayList<>();

		String statement = "select * from orders where user_id = ?";
		
		return jdbcTemplate.query(statement, new Object[] {userId}, rs -> {
			
			while(rs.next()) {
				Orders orders = new Orders();

				orders.setId(Integer.parseInt(rs.getString("id")));
				orders.setOrdNo(rs.getString("order_number"));
				orders.setCharges(rs.getString("total_charges"));
				orders.setStatus(rs.getString("order_status"));
				orders.setShipName(rs.getString("shipper_name"));
				orders.setShipMobile(rs.getString("shipper_mobile"));
				orders.setShipAddress(rs.getString("shipper_address"));
				orders.setDate(rs.getString("date"));

				arrayList.add(orders);
			}
			return arrayList;
		});
			
	}

	public Orders getOrderDetail(Long orderId) {
		
		Orders orders = new Orders();
		
		String statement = "select count(*) from orders where id = ?";
		int count = jdbcTemplate.queryForObject(statement, new Object[] {orderId}, Integer.class);

		boolean exist = false;	

		if(count > 0) {
			exist = true;
		}
			
		if(exist) {
			String orderDetailstatement = "select order_number, total_charges from orders where orders.id = ?";
			jdbcTemplate.query(orderDetailstatement, new Object[] {orderId}, rs -> {
				orders.setOrdNo(rs.getString("order_number"));
				orders.setCharges(rs.getString("total_charges"));
			});
		}
			
		return orders;
	}

	public List<Orders> getOrderProducts(Long orderId) {
		
		List<Orders> arrayList = new ArrayList<>();
		
			String statement = "select count(*) from orders where id = ?";
			int count = jdbcTemplate.queryForObject(statement, new Object[] {orderId}, Integer.class);

			boolean exist = false;	
			
			if(count > 0) {
				exist = true;
			}
			
			if(exist) {
				String orderDetailstatement = "select * from order_detail where orders_id =  ?";
				jdbcTemplate.query(orderDetailstatement, new Object[] {orderId}, rs -> {
			
					while(rs.next()) {
						Orders orders = new Orders();

						String productStatement = "select * from products where id = ?";
						
						jdbcTemplate.query(productStatement, new Object[] {rs.getInt("prod_id")}, rs1 -> {
						
							orders.setProdName(rs1.getString("name"));
							orders.setImage(rs1.getString("image"));
							orders.setCharges(rs.getString("prod_charges"));
							orders.setQuantity(rs.getString("quantity"));
							arrayList.add(orders);
							
						});
						
					}
					
					return arrayList;
				});
			}
//			 System.out.print("detail statement:"+orderDetailstatement);

			
			
		
		return arrayList;
	}

	public ResultType processOrders(String id, String orderStat) {
		
		ResultType status = ResultType.NOTHING;
		
		String statement = "select count(*) from orders where id = ?";

		int count = jdbcTemplate.queryForObject(statement, new Object[] {id}, Integer.class);

		if(count > 0) {
			String updtStmt = "update orders set order_status = ? where id = ?";
			int updatedRows = jdbcTemplate.update(updtStmt, new Object[] {orderStat, id});
			
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
