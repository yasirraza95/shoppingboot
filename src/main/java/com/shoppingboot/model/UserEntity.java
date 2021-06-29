package com.shoppingboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Username must not be empty")
	private String username;
	@NotEmpty(message="Email address must not be empty")
	@Email(message="Please enter valid email address")
	private String email;
	@NotEmpty(message="Name must not be empty")
	private String name;
	@NotEmpty(message="Mobile no must not be empty")
	private String mobile;
	@NotEmpty(message="Address must not be empty")
	private String address;
//	@NotEmpty(message="Password must not be empty")
	@Transient
	private String password;
	private String type;
	
//	public UserEntity(String email, String name, String mobile, String address) {
//		this.email = email;
//		this.name = name;
//		this.mobile = mobile;
//		this.address = address;
//	}
	
}
