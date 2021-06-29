package com.shoppingboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String urdu_name;
	private String description;
	
	@Column(name="image")
	private String imageName;
	private int price_type;
	private String top_product;
	private int price;
	private int status;
	private String type;
	private int active;
}
