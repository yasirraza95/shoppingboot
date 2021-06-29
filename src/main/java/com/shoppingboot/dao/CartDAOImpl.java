package com.shoppingboot.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;

public class CartDAOImpl implements CartDAO {
	
	private JdbcTemplate jdbcTemplate;	
	private Calendar calendar;
	private Date currentTime;
	public long time;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public ResultType addCart(Long userId, int prodId) {
		ResultType status = ResultType.NOTHING;

		calendar = Calendar.getInstance();
		currentTime = calendar.getTime();
		time = currentTime.getTime();
		
		int dbQty = 0;
		int quantity = 0;
		int existCounter = 0;
		
		String chkStmt = "select quantity from cart where user_id = ? and prod_id = ?";
//		dbQty = getJdbcTemplate().jdbcTemplate.queryForObject(chkStmt, new Object[] {userId, prodId}, Integer.class);
		
		try {
			dbQty = jdbcTemplate.queryForObject(chkStmt, new Object[] {userId, prodId}, Integer.class);	
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			dbQty = 0;
		}
		
	
		
		
		String existStmt = "select count(*) from cart where user_id = ? and prod_id = ?";
		existCounter = jdbcTemplate.queryForObject(existStmt, new Object[] {userId, prodId}, Integer.class);
		
		boolean exist = false;
		
			if (existCounter > 0) {
				exist = true;
			}
	
			if (exist) {
				
				String updtStmt = "update cart set quantity = ? where user_id = ? and prod_id = ?";
				quantity = dbQty + 1;
				
				int affectedRows = jdbcTemplate.update(updtStmt, new Object[] {quantity, userId, prodId});
				
				if(affectedRows > 0) {
					status = ResultType.UPDATED;	
				}
				
	
			} else {
				String insStmt =  "insert into cart (user_id, prod_id, quantity, time) values (?, ?, ?, ?)";
				
				int affectedRows = jdbcTemplate.update(insStmt, new Object[] {userId, prodId, 1, new Timestamp(time)});
				
				if(affectedRows > 0) {
					status = ResultType.CREATED;	
				}
				
				
	
			}

		return status;
	}

	public ResultType delCart(Long userId, int prodId) {
		ResultType status = ResultType.NOTHING;

		String	chkStmt = "select count(*) from cart where user_id = ? and prod_id = ?";
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {userId, prodId}, Integer.class);
		
			if (count > 0) {
				String delStmt = "delete from cart where user_id = ? and prod_id = ?";
				int deletedRows = jdbcTemplate.update(delStmt, new Object[] {userId, prodId});
				
				if(deletedRows > 0) {
					status = ResultType.DELETED;
				}
				
			}

		return status;
	}

	public ResultType updateCart(Long userId, int prodId, String type) {
		ResultType status = ResultType.NOTHING;

		String chkStmt = "select quantity from cart where user_id = ? and prod_id = ?";
		int dbQty = jdbcTemplate.queryForObject(chkStmt, new Object[] {userId, prodId}, Integer.class);

			if (dbQty > 0) {
				
				if(type.equals("up")) {
					dbQty = dbQty + 1;
				} else {
					dbQty = dbQty - 1;
				}
				
				
				String updateStmt = "update cart set quantity = ? where user_id = ? and prod_id = ?";
				int updatedRows = jdbcTemplate.update(updateStmt, new Object[] {dbQty, userId, prodId});
				
				if(updatedRows > 0) {
					status = ResultType.UPDATED;
				}
				
			}
			

		return status;
	}

	public List<Cart> getCart(Long userId) {
		
		List<Cart> arrayList = new ArrayList<>();
		
		String statement = "select products.image as image, products.name as name, products.price as price,"
					+ " cart.quantity as quantity, cart.id as id, cart.prod_id as prod_id from cart inner join users"
					+ " on cart.user_id = users.id inner join products on cart.prod_id = products.id where users.id = ? ";
//			System.out.println("userid"+userId);
			arrayList = jdbcTemplate.query(statement, new Object[] {userId}, rs -> {
				List<Cart> cartList = new ArrayList<>();

				while(rs.next()) {
					Cart cart = new Cart();
					cart.setId(Integer.parseInt(rs.getString("id")));
					cart.setProdId(Integer.parseInt(rs.getString("prod_id")));
					cart.setName(rs.getString("name"));
					cart.setImage(rs.getString("image"));
					cart.setPrice(rs.getString("price"));
					cart.setQuantity(rs.getString("quantity"));

					cartList.add(cart);
					
				}

				return cartList;
			});

		return arrayList;
	}

}
