package com.shoppingboot.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shoppingboot.model.UserEntity;
import com.shoppingboot.model.UserPassEntity;
import com.shoppingboot.model.UserPassModel;
import com.shoppingboot.repository.UserPassRepository;
import com.shoppingboot.repository.UserRepository;
import com.shoppingboot.model.TgtUser;

@Service
@Transactional
public class AccountHServiceImpl implements AccountHService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserPassRepository userPassRepo;
	
	@Autowired 
	PasswordEncoder passwordEncoder;	
	
	private Authentication auth = null;
	
	@Override
	public UserEntity getCurrentUser() {
	
		auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepo.findOneByUsername(auth.getName());
	}

	@Override
	public UserEntity getAdmin() {
		return userRepo.findOneByUsername("admin");
	}

	@Override
	public String update(UserEntity user) {
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
//		System.out.println(user.getAddress());
//		int status = userRepo.updateProfile(user.getName(), user.getEmail(), user.getMobile(), user.getAddress(), ""+tgtUser.getId());
		
		UserEntity userEntity = userRepo.findById((long) tgtUser.getId()).get();
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setMobile(user.getMobile());
		userEntity.setAddress(user.getAddress());
		userEntity = userRepo.save(userEntity);
				
		//check if update done by default method
		String returnStatus = null;
		
		if(userEntity != null) {
			returnStatus = "Account updated successfully";
		} else {
			returnStatus = "Account not updated, try again";
		}
		
		return returnStatus;
	}
	
	@Override
	public String updatePassword(UserPassModel user) {
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
				
		String encryptedPassword = passwordEncoder.encode(user.getNewPassword());
		
		UserPassEntity userEntity = userPassRepo.findById((long) tgtUser.getId()).get();
		userEntity.setPassword(encryptedPassword);
		userEntity = userPassRepo.save(userEntity);
				
		//check if update done by default method
		String returnStatus = null;
		
		if(userEntity != null) {
			returnStatus = "Account updated successfully";
		} else {
			returnStatus = "Account not updated, try again";
		}
		
		return returnStatus;
	}
}