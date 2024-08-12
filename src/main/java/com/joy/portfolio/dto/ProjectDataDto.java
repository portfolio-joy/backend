package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public class ProjectDataDto {
	
	@NotNull
	private String heading;

	@NotNull
	private String description;
	
	@NotNull
	private MultipartFile image;
}
