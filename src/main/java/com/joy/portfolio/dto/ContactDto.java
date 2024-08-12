package com.joy.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ContactDto {

	@NotNull
	@Email
	private String emailId;

	@NotNull
	private String phoneNo;

	@NotNull
	private String address;
}
