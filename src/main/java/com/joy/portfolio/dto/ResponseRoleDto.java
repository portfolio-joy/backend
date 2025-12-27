package com.joy.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRoleDto {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("userCount")
	private long userCount;
}