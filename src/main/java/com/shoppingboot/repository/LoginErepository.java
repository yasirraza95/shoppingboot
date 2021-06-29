package com.shoppingboot.repository;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;

public interface LoginErepository {

	public ResultType validate(User user);
}
