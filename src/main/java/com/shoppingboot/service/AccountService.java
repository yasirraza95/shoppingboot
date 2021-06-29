package com.shoppingboot.service;

import com.shoppingboot.model.User;

public interface AccountService {
	public User getCurrentUser();
	public User getAdmin();
	public String update(User user);
}
