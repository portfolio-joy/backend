package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.portfolio.annotation.ValidFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AboutMeDto {
	
	@JsonIgnore
	private String id;
	
	@NotBlank(message = "Name must not be empty")
	private String name;
	
	@NotBlank(message = "Description must not be empty")
	@Size(min = 1,max = 600)
	private String description;

	@NotBlank(message = "Skills must not be empty")
	private String skills;
	
	@JsonIgnore
	@ValidFile(fileType = "image/", message = "Invalid Image Type or Image size is larger than 10 MB")
	private MultipartFile image;
}