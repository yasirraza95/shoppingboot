package com.shoppingboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shoppingboot.model.UserEntity;
import com.shoppingboot.model.UserPassEntity;

public interface UserPassRepository extends CrudRepository<UserPassEntity, Long> {

	@Query(value="select * from users where username = ?1", nativeQuery=true)
	UserPassEntity getCurrentUser(String username);
	
	UserPassEntity findOneById(long id);
}
