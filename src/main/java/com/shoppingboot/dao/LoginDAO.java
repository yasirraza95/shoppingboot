package com.shoppingboot.dao;

import com.shoppingboot.model.User;
import com.shoppingboot.enums.ResultType;

public interface LoginDAO {
	public ResultType validate(User user);

}
