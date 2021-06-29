package com.shoppingboot.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.CartEntity;
import com.shoppingboot.model.CartModelInterface;
import com.shoppingboot.model.OrderDetailEntity;

public interface CartRepository extends CrudRepository<CartEntity, Long> {

	@Query(value="select p.image as image, p.name as name, p.price as price, c.quantity as quantity, c.id as id, c.user_id as userId,"
			+ " c.prod_id as prodId from cart c inner join users u on c.user_id = u.id inner join products p on"
			+ " c.prod_id = p.id where u.id = ?1 ", nativeQuery=true)	
	public List<CartModelInterface> getCart(Long userId);
	
	@Query(value="select quantity from cart where user_id = ?1 and prod_id = ?2", nativeQuery=true)
	public String checkQuantity(Long userId, int prodId);
		
	@Query(value="select count(*) from cart where user_id = ?1", nativeQuery=true)
	public String getCartCounter(int userId);
	
	public String countByUserId(Long userId);
	
	@Query(value="select count(quantity) from cart where user_id = ?1 and prod_id = ?2", nativeQuery=true)
	public int checkCart(int userId, int prodId);
	
	public CartEntity findOneByUserIdAndProdId(Long userId, int prodId);
	
	@Modifying
	@Transactional
	@Query(value="insert into cart set user_id = ?1, prod_id = ?2, quantity = 1", nativeQuery=true)
	public int addCart(Long userId, int prodId);
	
	@Modifying
	@Transactional
	@Query(value="update cart set quantity = ?3 where user_id = ?1 and prod_id = ?2", nativeQuery=true)
	public int updateCart(Long userId, int prodId, int quantity);
	
	public boolean existsByUserIdAndProdId(Long userId, int prodId);
	
	@Modifying
	@Transactional
	@Query(value="delete from cart where user_id = ?1 and prod_id = ?2", nativeQuery=true)
	public int removeCart(Long userId, int prodId);
	
	@Modifying
	@Transactional
	void deleteByUserIdAndProdId(Long userId, int prodId);
	
	@Modifying
	@Transactional
	@Query(value="delete from cart where user_id = ?1", nativeQuery=true)
	public int removeCart(int userId);
	
	void deleteByUserId(Long userId);
}
