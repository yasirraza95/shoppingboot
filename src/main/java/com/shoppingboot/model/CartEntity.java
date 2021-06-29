package com.shoppingboot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cart")
@Data
public class CartEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	private int prodId;
	private int quantity;
//	private String time;
	
	public CartEntity() { }
		
	@OneToMany(mappedBy="cartEntity")
//	@OneToMany(targetEntity=CartEntity.class, mappedBy = "id", orphanRemoval=false)
	List<ProductsEntity> productsEntity;
}
