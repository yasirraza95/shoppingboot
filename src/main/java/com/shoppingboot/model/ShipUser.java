package com.shoppingboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class ShipUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	
	private String orderNumber;
	private String date;
	private String orderCharges;
	private String totalCharges;
	private String orderStatus;
	private int paymentStatus;
	
	@NotEmpty(message="Username is required")
	private String username;
		
	@Column(name="shipper_email")
	@NotEmpty(message="Email is required")
	@Email(message="Please enter valid email")
	private String email;
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@Column(name="shipper_mobile")
	@NotEmpty(message="Mobile is required")
	private String mobile;
	
	@Column(name="shipper_address")
	@NotEmpty(message="Address is required")
	private String address;
	
	private String type;
	

}
