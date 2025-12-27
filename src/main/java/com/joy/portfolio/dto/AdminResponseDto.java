package com.joy.portfolio.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joy.portfolio.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminResponseDto {
	
	@JsonProperty("users")
	private List<User> users;
	
	@JsonProperty("roles")
	private List<ResponseRoleDto> roles;
}