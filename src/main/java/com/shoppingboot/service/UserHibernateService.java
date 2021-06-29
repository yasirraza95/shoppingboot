package com.shoppingboot.service;

import java.util.List;
import org.springframework.stereotype.Component;
import com.shoppingboot.model.UserEntity;

@Component
public interface UserHibernateService {

	UserEntity getSingleUser(int id);
	List<UserEntity> getUsers(String type);
}
