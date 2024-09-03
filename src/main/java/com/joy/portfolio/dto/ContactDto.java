package com.joy.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ContactDto {

	@NotNull
	@Email
	private String emailId;

	@NotNull
	@Pattern(regexp="^[6-9]\\d{9}$", message="Invalid Phone Number")
	private String phoneNo;

	@NotNull
	private String address;
}
