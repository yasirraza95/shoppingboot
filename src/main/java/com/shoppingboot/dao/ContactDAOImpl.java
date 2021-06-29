package com.shoppingboot.dao;

import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;

import com.shoppingboot.enums.ResultType;

public class ContactDAOImpl implements ContactDAO{

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public ResultType insert(String name, String email, String reason, String message, Long time) {
		
		ResultType status = ResultType.NOTHING;
		
		String insertMsg = "insert into contact (name, email, reason, message, date) VALUES(?, ?, ?, ?, ?)";
		int insertedRows = jdbcTemplate.update(insertMsg, new Object[] {name, email, reason, message, new Timestamp(time)}, Integer.class);
		
		if(insertedRows > 0) {
			status = ResultType.CREATED;
		}
		
		return status;
	}

}
