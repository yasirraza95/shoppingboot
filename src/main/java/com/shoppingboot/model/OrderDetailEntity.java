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
@Table(name="order_detail")
@Data
public class OrderDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="orders_id")
	private int orderId;
	
	@Column(name="prod_id")
	private int prodId;
	
	@Column(name="prod_charges")
	private int charges;

	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="prod_id", insertable=false, updatable=false)
	private ProductsEntity products; 
	
	
	@ManyToOne
	@JoinColumn(name="orders_id", insertable=false, updatable=false)
	OrdersEntity ordersEntity;
}
