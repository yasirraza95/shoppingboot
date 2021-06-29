package com.shoppingboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shoppingboot.dao.UserDAO;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;

@Component
public class SignupServiceImpl implements SignupService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public String insert(User user) {
		
		ResultType status = userDAO.insert(user);
		String returnStatus = status.toString();
		
		if(returnStatus.equals("CREATED")) {
			returnStatus = "Account created successfully";
		} else if(returnStatus.equals("ALREADY")) {
			returnStatus = "Username or Email already exists";
		} else {
			returnStatus = "Account not created, try again";
		}
		
		return returnStatus;
	}

}
