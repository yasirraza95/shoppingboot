package com.shoppingboot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public ResultType insert(User user) {
		ResultType status = ResultType.NOTHING;
		
		String counterQuery = "select count(*) from users where username = ? or email = ?";
		
		int count = jdbcTemplate.queryForObject(counterQuery, new Object[] {user.getUsername(), user.getEmail()}, Integer.class);
		boolean exist = false;
		
		if(count > 0) {
			exist = true;
			status = ResultType.ALREADY;
		}
		
		if(!exist) {
			
			String insertUser = "insert into users (name, email, mobile, address, username, password, type) VALUES(?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(insertUser, new Object[] {user.getName(), user.getEmail(), user.getMobile(),
					user.getAddress(), user.getUsername(), user.getPassword(), user.getType()}, new int[] {Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, });
			
			if(rowsInserted > 0) {
				status = ResultType.CREATED;
			} else {
				status = ResultType.NOTHING;	
			}
		}
		
		return status;
	}

	public ResultType updateProfile(User user) {
		
		ResultType status = ResultType.NOTHING;

		String getUser = "select count(*) from users where id = ?";
		int count = jdbcTemplate.update(getUser, new Object[] {user.getUserId()}, Integer.class);
						
		System.out.println("counter:"+user.getUserId());

		boolean exist = false;
		
		if(count > 0) {
			exist = true;
		} 
			
		if(exist){
			String updateUser = "update users set name = ? , email = ? , mobile = ? , address = ? where id = ? ";
			int updatedRows = jdbcTemplate.update(updateUser, new Object[] {user.getName(), user.getEmail(), user.getMobile(),
					user.getAddress(), user.getUserId()});
				System.out.println("update info:"+updateUser);
			
			if(updatedRows > 0) {
				status = ResultType.UPDATED;
			}
			
		} else {
			status = ResultType.NOTHING;
		}
			
		
		return status;
	}

	public ResultType updatePassword(int userId, String oldPassword, String newPassword) {
		
		ResultType status = ResultType.NOTHING;

		String getUser = "select count(*) from users where id = ? and password = ?";
		int count = jdbcTemplate.update(getUser, new Object[] {userId, oldPassword}, Integer.class);
						
		boolean exist = false;

		if(count == 0) {
			exist = true;
		} 
			
		if(exist) {
			String updatePass = "update users set password = ? where id = ? ";
			int updatedRows = jdbcTemplate.update(updatePass, new Object[] {newPassword, userId}, Integer.class);
			
			if(updatedRows > 0) {
				status = ResultType.UPDATED;
			}
		}

		return status;
	}

	public List<User> getUsers(String type) {

		List<User> arrayList = new ArrayList<>();
		
		String statement = "select * from users where type = ?";
		
		arrayList = jdbcTemplate.query(statement, new Object[] {type}, rs -> {
			List<User> userList = new ArrayList<>();
			
			while(rs.next()) {
				User user = new User();
				user.setUserId(Integer.parseInt(rs.getString("id")));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				user.setType(rs.getString("type"));
				
				userList.add(user);
			}
			return userList;
		});
			
			
		return arrayList;	
	} 
		
	
	@SuppressWarnings("deprecation")
	public User getSingleUser(String username) {

		String singleUser = "select * from users where username = ?";

        return jdbcTemplate.queryForObject(singleUser, new Object[]{username}, (rs, rowNum) ->
            new User(
        		rs.getInt("id"),
        		rs.getString("name"),
				rs.getString("username"),
				rs.getString("mobile"),
				rs.getString("email"),
				rs.getString("address"),
				rs.getString("password")
            ));

    }
	
	//
	public TgtUser getUser(String username, String type) {
		String sql = "select u.id, u.username, u.password, u.enabled, u.type, a.authority from users u inner join authorities a on u.username"
				+ " = a.username where u.enabled = 'true' and u.username = ? and u.type = ? ";
//		System.out.println("query="+sql);
		TgtUser userInfo = (TgtUser)jdbcTemplate.queryForObject(sql, new Object[]{username, type},
	    		new RowMapper<TgtUser>() {
		            public TgtUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	
 		            	TgtUser user = new TgtUser();
 		            	user.setId(rs.getInt("id"));
 		            	user.setUsername(rs.getString("username"));
		                user.setPassword(rs.getString("password"));
		                user.setRole(rs.getString("authority"));
		                user.setType(rs.getString("type"));
		                
		                return user;
		            }
	        });

		return userInfo;
	}
	//
	
	public User getSingleUser(int id) {

		String singleUser = "select * from users where id = ?";

        return jdbcTemplate.queryForObject(singleUser, new Object[]{id}, (rs, rowNum) ->
            new User(
        		rs.getString("name"),
				rs.getString("username"),
				rs.getString("mobile"),
				rs.getString("email"),
				rs.getString("address"),
				rs.getString("password")
            ));

    }

	public ResultType delUser(int userId) {

		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from users where id = ? ";
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {userId}, Integer.class);

		if(count > 0) {
			String delStmt = "delete from users where id = ?";
			int deletedRows = jdbcTemplate.update(delStmt, new Object[] {userId});
			
			if(deletedRows > 0) {
				status = ResultType.UPDATED;
			}
		} 
			
		return status;
	}

	public ResultType validateUser(String username, String email) {

		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from users where username = ? or email = ? ";	
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {username, email}, Integer.class);	
		
		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 
			
		return status;
	}

	public ResultType validateProvider(String username) {

		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from users where username = ? ";	
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {username}, Integer.class);

		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 
		
		return status;
	}

}
