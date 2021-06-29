package com.shoppingboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.shoppingboot.model.UserEntity;

@Component
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	@Query(value="select * from users where username = ?1", nativeQuery=true)
	UserEntity getCurrentUser(String username);
	
	UserEntity findOneByUsername(String username);
	
	@Modifying
	@Query(value="update users set name = ?1, email =?2, mobile =?3, address=?4 where id = ?5", nativeQuery=true)
	int updateProfile(String name, String email, String mobile, String address, String id);

	@Query(value="select * from users where id = ?1", nativeQuery=true)
	UserEntity getSingleUser(int id);
	
	UserEntity findOneById(int id);
	
	@Query(value="select * from users where id = ?1", nativeQuery=true)
	List<UserEntity> getUsers(String type);
	
	List<UserEntity> findAllById(String id);
}
