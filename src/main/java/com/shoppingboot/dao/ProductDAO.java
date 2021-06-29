package com.shoppingboot.dao;

import java.util.List;

import com.shoppingboot.model.Product;
import com.shoppingboot.enums.ResultType;

public interface ProductDAO {
	public List<Product> getProducts(String prodType);
	public List<Product> getTopProducts();
	public Product getSingleProduct(int id);
	public ResultType insert(Product product);
	public ResultType delProduct(int id);
	public ResultType updateProd(Product product);
	public ResultType validateProduct(String name);
	
}
