package com.shoppingboot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;

public class LoginDAOImpl implements LoginDAO{

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public ResultType validate(User user) {

		ResultType status = ResultType.NOTHING;
		
		
			String statement = "select * from users where username = ? and password = ? and type = ?";
			int counter = jdbcTemplate.update(statement, new Object[] {user.getUsername(), user.getPassword(), user.getType()}, Integer.class);

			if(counter > 0) {
				
				int userId = jdbcTemplate.query(statement, new Object[] {user.getUsername(), user.getPassword(), user.getType()}, rs -> {
					
					int result = 0;
					while(rs.next()) {
						result = (Integer.parseInt(rs.getString("id")));
					}
					return result;
				});
				
				
				String cartStatement = "select count(*) from cart where user_id = ?";
				int cartCounter = jdbcTemplate.update(cartStatement, new Object[] {userId}, Integer.class);
				
				user.setCartQty(cartCounter);
				
				jdbcTemplate.query(statement, new Object[] {user.getUsername(), user.getPassword(), user.getType()}, rs -> {
				
					user.setUserId(Integer.parseInt(rs.getString("id")));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setMobile(rs.getString("mobile"));
					user.setAddress(rs.getString("address"));
					user.setType(rs.getString("type"));
				});
				
				status = ResultType.FOUND;
				
			}

		return status;
	}

}
