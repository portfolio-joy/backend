package com.joy.portfolio.dto;

import com.joy.portfolio.annotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterDto {

	@NotEmpty(message = "First Name must not be empty")
	@Pattern(regexp = "^[a-zA-Z]$", message = "first name should only consists of alphabets")
	String firstName;

	@Pattern(regexp = "^[a-zA-Z]$", message = "first name should only consists of alphabets")
	String lastName;

	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email Id is not valid")
	@NotEmpty(message = "Email Id must not be empty")
	String emailId;

	@NotEmpty(message = "Username must not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "username must be of 6 to 12 length with no special characters")
	String username;

	@NotEmpty(message = "Password must should not be empty")
	@ValidPassword
	String password;
}