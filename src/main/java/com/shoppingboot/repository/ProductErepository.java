package com.shoppingboot.repository;

import java.util.List;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Product;

public interface ProductErepository {

	public List<Product> getProducts(String prodType);
	public List<Product> getTopProducts();
	public Product getSingleProduct(int id);
	public ResultType insert(Product product);
	public ResultType delProduct(int id);
	public ResultType updateProd(Product product);
	public ResultType validateProduct(String name);
}
