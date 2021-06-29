package com.shoppingboot.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class ProductsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String prodName;
	@Column(name="image")
	private String imageName;
	private String price;
	
	@OneToMany(targetEntity=OrderDetailEntity.class, mappedBy = "id", orphanRemoval=false)
	private List<OrderDetailEntity> orderDetail;
	
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	CartEntity cartEntity;
}
