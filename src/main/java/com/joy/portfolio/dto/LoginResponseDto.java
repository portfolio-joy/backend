package com.joy.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("token")
	private String token;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("role")
	private String role;
}