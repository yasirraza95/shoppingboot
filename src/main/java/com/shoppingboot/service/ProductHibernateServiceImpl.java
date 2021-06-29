package com.shoppingboot.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.shoppingboot.model.Product;
import com.shoppingboot.model.ProductEntity;
import com.shoppingboot.repository.ProductRepository;

@Service
@Transactional
public class ProductHibernateServiceImpl implements ProductHibernateService {


	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductEntity> getTopProducts() {
		return productRepository.getTopProducts();
	}
	
	@Override
	public List<ProductEntity> getCategoryProducts(String type) {
//		return productRepository.getCategoryProducts(type);
		return productRepository.findAllByType(type);
	}

	@Override
	public ProductEntity getSingleProduct(int id) {
//		return productRepository.getSingleProduct(id);
		return productRepository.findOneById(id);
	}
	
	@Override
	public List<ProductEntity> getProducts(String prodType) {
//		return productRepository.getCategoryProducts(prodType);
		return productRepository.findAllByType(prodType);
	}
	
	@Override
	public void insert(Product product) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(product.getName());
		productEntity.setImageName(product.getImageName());
		productEntity.setPrice(product.getPrice());
		productEntity.setType(product.getType());
		
//		productRepository.insert(product.getName(), product.getImageName(), product.getPrice(), product.getType());
		productRepository.save(productEntity);
	}
	
}
