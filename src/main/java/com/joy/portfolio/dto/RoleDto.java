package com.joy.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDto {
	
	@NotBlank(message = "Role name must not be empty")
	@Size(max = 17, message = "Role name length should be less than 18")
	private String name;
}