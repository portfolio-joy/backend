package com.joy.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContactDto {

	@NotBlank(message = "EmailId must not be empty")
	@Email
	private String emailId;

	@NotBlank(message = "Phone Number must not be empty")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Phone Number")
	private String phoneNo;

	@NotBlank(message = "Address must not be empty")
	private String address;
}