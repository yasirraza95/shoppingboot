package com.shoppingboot.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingboot.model.UserEntity;
import com.shoppingboot.repository.UserRepository;

@Service
@Transactional
public class UserHibernateServiceImpl implements UserHibernateService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserEntity getSingleUser(int id) {
//		return userRepo.getSingleUser(id);
		return userRepo.findOneById(id);
	}

	@Override
	public List<UserEntity> getUsers(String type) {
//		return userRepo.getUsers(type);
		return userRepo.findAllById(type);
	}

	
}
