package com.shoppingboot.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userId;

	@NotEmpty(message="Name is required")
	private String name;
	
	@NotEmpty(message="Email is required")
	@Email(message="Valid email is required")
	private String email;
	
	@NotEmpty(message="Username is required")
	private String username;
	
	@NotEmpty(message="Mobile is required")
	private String mobile;
	
	@NotEmpty(message="Address is required")
	private String address;
	
}
