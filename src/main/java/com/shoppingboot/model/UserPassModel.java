package com.shoppingboot.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserPassModel {
	
	@NotEmpty(message="Old password must not be empty")
	@Size(min=5, max=10)
	private String oldPassword;
	@NotEmpty(message="New password must not be empty")
	@Size(min=5, max=10)
	private String newPassword;
	@NotEmpty(message="Confirm password must not be empty")
	@Size(min=5, max=10)
	private String confirmPassword;

}
