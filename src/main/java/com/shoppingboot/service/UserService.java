package com.shoppingboot.service;

import java.util.List;

import com.shoppingboot.model.User;

public interface UserService {

	public User getSingleUser(int id);
	public List<User> getUsers(String type);
	
}
