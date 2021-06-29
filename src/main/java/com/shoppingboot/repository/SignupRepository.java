package com.shoppingboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.UserEntity;

public interface SignupRepository extends CrudRepository<UserEntity, Long> {

	@Query(value="insert into users set username = ?1, password = ?2, email = ?3 name = ?4, mobile = ?5, "
			+ "address = ?6, type = 'user'", nativeQuery=true)
	public ResultType insert(String username, String password, String email, String name, String mobile, 
			String address);
	
	public UserEntity findByUsernameOrEmail(String username, String email);
}
