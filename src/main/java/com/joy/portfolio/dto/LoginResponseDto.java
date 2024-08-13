package com.joy.portfolio.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
	
	private String id;
    private String token;
    private long expiresIn;
}
