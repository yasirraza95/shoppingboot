package com.shoppingboot.service;

import org.springframework.stereotype.Component;

import com.shoppingboot.model.User;

@Component
public interface SignupHibernateService {

	public String insert(User user);

}
