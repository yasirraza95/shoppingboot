package com.shoppingboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.shoppingboot.model.Product;
import com.shoppingboot.model.ProductEntity;

@Component
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

	@Query(value="select * from products order by rand() limit 4", nativeQuery=true)
	public List<ProductEntity> getTopProducts();
	
//	public List<ProductEntity> findTopFour();
	
	@Query(value="select * from products where type =?1", nativeQuery=true)
	public List<ProductEntity> getCategoryProducts(String type);

	public List<ProductEntity> findAllByType(String type);
	
	@Query(value="select * from products where id =?1", nativeQuery=true)
	public ProductEntity getSingleProduct(int id);
	
	public ProductEntity findOneById(int id);
	
	@Query(value="insert into products set name ?1, image = ?2, price = ?3, type =?4", nativeQuery=true)
	public void insert(String name, String image, int price, String type);
}
