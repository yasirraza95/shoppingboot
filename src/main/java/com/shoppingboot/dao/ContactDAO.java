package com.shoppingboot.dao;

import com.shoppingboot.enums.ResultType;

public interface ContactDAO {
	public ResultType insert(String name, String email, String reason, String message, Long time);

}
