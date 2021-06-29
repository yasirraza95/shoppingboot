package com.shoppingboot.model;
import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<MultipartFile> image;
	private String imageName;
	private String description;
	private int price;
	private String priceType;
	private String type;
	private String topProduct;
	private String productType;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<MultipartFile> getImage() {
		return image;
	}
	
	public void setImage(List<MultipartFile> image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getPriceType() {
		return priceType;
	}
	
	public void setPriceType(String pricetype) {
		this.priceType = pricetype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTopProduct() {
		return topProduct;
	}

	public void setTopProduct(String topProduct) {
		this.topProduct = topProduct;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
