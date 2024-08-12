package com.joy.portfolio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
	
	@NotNull
	private String credential;
	
	@NotNull
	private String password;
}
