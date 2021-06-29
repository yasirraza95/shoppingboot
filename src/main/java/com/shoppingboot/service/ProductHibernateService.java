package com.shoppingboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppingboot.model.Product;
import com.shoppingboot.model.ProductEntity;

@Component
public interface ProductHibernateService {
	
	public List<ProductEntity> getTopProducts();
	public List<ProductEntity> getCategoryProducts(String type);
	public ProductEntity getSingleProduct(int id);
	public List<ProductEntity> getProducts(String prodType);
	public void insert(Product product);
}
