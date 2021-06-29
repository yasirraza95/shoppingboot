package com.shoppingboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shoppingboot.dao.UserDAO;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;

@Component 
public class AccountServiceImpl implements AccountService{

	@Autowired
	UserDAO userDAO;
	
	public User getCurrentUser() {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		User user = userDAO.getSingleUser(tgtUser.getUsername());
		return user;
	}
	
	public User getAdmin() {
		
		User user = userDAO.getSingleUser("admin");
		return user;
	}
	
	@Override
	public String update(User user) {
		System.out.println(user.getAddress());
		ResultType status = userDAO.updateProfile(user);
		String returnStatus = status.toString();
		
		if(returnStatus.equals("UPDATED")) {
			returnStatus = "Account updated successfully";
		} else {
			returnStatus = "Account not updated, try again";
		}
		
		return returnStatus;
	}
	
}
