package com.shoppingboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppingboot.dao.UserDAO;
import com.shoppingboot.model.User;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public User getSingleUser(int id) {
		User user = userDAO.getSingleUser(id);
		return user;
	}
	
	public List<User> getUsers(String type) {
		List<User> user = userDAO.getUsers(type);
		return user;
	}

}
