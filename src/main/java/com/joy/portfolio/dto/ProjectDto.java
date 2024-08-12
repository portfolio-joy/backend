package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public class ProjectDto {
	
	@NotNull
	private String name;

	@NotNull
	private String briefDetail;

	@NotNull
	private MultipartFile image;
}
