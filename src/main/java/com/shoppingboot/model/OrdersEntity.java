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
import javax.persistence.Transient;
import javax.validation.constraints.Null;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Table(name="orders")
@Data
public class OrdersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id;
	@Column(name="order_number")
	private String ordNo;
	@Column(name="order_charges")
	private String charges;
	@Transient
	private String name;
	@Transient
	private String mobile;
	@Transient
	private String email;
	@Transient
	private String address;
	@Transient
	private String prodName;
	@Transient
	private String quantity;
	@Transient
	private String image;
	private String status;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="shipper_name")
	private String shipName;
	@Column(name="shipper_mobile")
	private String shipMobile;
	@Column(name="shipper_email")
	private String shipEmail;
	@Column(name="shipper_address")
	private String shipAddress;
	private String date;
	
	public OrdersEntity() { }
	
	public OrdersEntity(long userId, String name, String mobile, String email, String address, String orderStatus) {
		this.user_id = userId;
		this.shipName = name;
		this.shipMobile = mobile;
		this.shipEmail = email;
		this.shipAddress = address;
		this.orderStatus = orderStatus;
	}

	@OneToMany(mappedBy="ordersEntity")
	List<OrderDetailEntity> orderDetailEntity;

}
