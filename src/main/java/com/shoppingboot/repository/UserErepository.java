package com.shoppingboot.repository;

import java.util.List;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;

public interface UserErepository {

	public ResultType insert(User user);
	public ResultType updateProfile(User user);
	public ResultType updatePassword(int userId, String oldPassword, String newPassword);
	public List<User> getUsers(String type);
	public User getSingleUser(String username);
	public TgtUser getUser(String username, String type);
	public User getSingleUser(int id);
	public ResultType delUser(int userId);
	public ResultType validateUser(String username, String email);
	public ResultType validateProvider(String username);
}
