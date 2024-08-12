package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public class AboutMeDto {
	
	@NotNull
	private String description;

	@NotNull
	private String skills;
	
	@NotNull
	private MultipartFile profile;
}
