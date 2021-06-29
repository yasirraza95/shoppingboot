package com.shoppingboot.service;

import java.util.List;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Product;

public interface ProductService {

	public Product getSingleProduct(int id);
	public List<Product> getProducts(String type);
	public List<Product> getTopProducts();
	public ResultType insert(Product product); 
}
