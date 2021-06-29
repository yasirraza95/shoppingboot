package com.shoppingboot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;
import com.shoppingboot.model.UserEntity;
import com.shoppingboot.repository.SignupRepository;

@Service
@Transactional
public class SignupHibernateServiceImpl implements SignupHibernateService {

	@Autowired
	SignupRepository signupRepo;
	
	@Override
	public String insert(User user) {
//		ResultType status = signupRepo.insert(user.getUsername().toString(), user.getPassword().toString(), user.getEmail().toString()
//				, user.getName().toString(), user.getMobile().toString(), user.getAddress().toString());
		
		
		ResultType status = ResultType.NOTHING;
		String returnStatus = status.toString();

		
		UserEntity checkUser = signupRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail());
		
		if(checkUser.getId() > 0) {
			returnStatus = "Username or Email already exists";

		} else {
			
			UserEntity userEntity = new UserEntity(); 
			userEntity.setUsername(user.getUsername());
			userEntity.setPassword(user.getPassword());
			userEntity.setEmail(user.getEmail());
			userEntity.setName(user.getName());
			userEntity.setMobile(user.getMobile());
			userEntity.setAddress(user.getAddress());
			userEntity = signupRepo.save(userEntity);
			
			
			if(userEntity.getId() > 0) {
				returnStatus = "Account created successfully";
			} else {
				returnStatus = "Account not created, try again";
			}
			
		}
		
		
		
//		if(returnStatus.equals("CREATED")) {
//			returnStatus = "Account created successfully";
//		} else if(returnStatus.equals("ALREADY")) {
//			returnStatus = "Username or Email already exists";
//		} else {
//			returnStatus = "Account not created, try again";
//		}
		
		return returnStatus;
	}

}
