package com.shoppingboot.service;

import org.springframework.stereotype.Component;

import com.shoppingboot.model.User;
import com.shoppingboot.model.UserEntity;
import com.shoppingboot.model.UserPassEntity;
import com.shoppingboot.model.UserPassModel;

@Component
public interface AccountHService {
	public UserEntity getCurrentUser();
	public UserEntity getAdmin();
	public String update(UserEntity user);
	public String updatePassword(UserPassModel user);
}
