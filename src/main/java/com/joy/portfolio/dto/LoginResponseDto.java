package com.joy.portfolio.dto;


import lombok.Data;

@Data

public class LoginResponseDto {
	
    private String token;
    private long expiresIn;
}
