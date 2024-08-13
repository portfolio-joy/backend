package com.joy.portfolio.dto;

import com.joy.portfolio.annotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
	
	@NotEmpty(message = "First Name must not be empty")
	String firstName;
	
	String lastName;
	
	@Email(message = "Email Id is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email Id must not be empty")
	String emailId;
	
	@NotEmpty(message = "Username must not be empty")
	@Size(min = 6, 	max = 16)
	String username;
	
	@NotEmpty(message = "Password must should not be empty")
	@ValidPassword
	String password;	
}