package com.shoppingboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserPassEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String password;
	
	@Transient
	private String oldPassword;
	
	@Transient
	private String newPassword;
	
	@Transient
	private String confirmPassword;
	
	
}
