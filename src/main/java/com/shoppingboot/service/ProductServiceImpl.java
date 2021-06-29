package com.shoppingboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppingboot.dao.ProductDAO;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Product;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	public Product getSingleProduct(int id) {
		Product product = productDAO.getSingleProduct(id);
		return product;
	}
	
	public List<Product> getProducts(String type) {
		
		List<Product> product = productDAO.getProducts(type);
		return product;
	}
	
	public List<Product> getTopProducts() {
		
		List<Product> product = productDAO.getTopProducts();
		return product;
	}
	
	public ResultType insert (Product product) {
		ResultType rs = ResultType.NOTHING;
		rs = productDAO.insert(product);
		
		return rs;
	}
	
}
