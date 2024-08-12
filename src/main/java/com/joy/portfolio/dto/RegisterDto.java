package com.joy.portfolio.dto;

import com.joy.portfolio.annotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	int age;
	
	@NotNull
	@Email
	private String emailId;
	
	@NotNull
	private String username;
	
	@NotNull
	@ValidPassword
	private String password;	
}