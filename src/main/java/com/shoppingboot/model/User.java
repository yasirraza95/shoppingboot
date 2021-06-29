package com.shoppingboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.shoppingboot.model.User;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	
	@NotEmpty(message="Username is required")
	private String username;
	
	@NotEmpty
	@Size(min=6, max=10, message="Password must be 6 to characters long")
	private String password;
	
	@NotEmpty(message="Confirm Password is required")
	@Size(min=6, max=10, message="Password must be 6 to characters long")
	private String cnfPassword;
	
	private String newPassword;
	private String oldPassword;
	
	@NotEmpty(message="Email is required")
	@Email(message="Please enter valid email")
	private String email;
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotEmpty(message="Mobile is required")
	private String mobile;
	
	@NotEmpty(message="Address is required")
	private String address;
	
	private String type;
	
	private int cartQty;

	public User() {
		super();
	}
	
	public User(String name, String username, String mobile, String email, String address, String password) {
		super();
		this.name = name;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.password = password;
	}
	
	public User(int userId, String name, String username, String mobile, String email, String address, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public User setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getCnfPassword() {
		return cnfPassword;
	}

	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public User setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public User setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getType() {
		return type;
	}

	public User setType(String type) {
		this.type = type;
		return this;
	}

	public int getCartQty() {
		return cartQty;
	}

	public User setCartQty(int cartQty) {
		this.cartQty = cartQty;
		return this;
	}
}
