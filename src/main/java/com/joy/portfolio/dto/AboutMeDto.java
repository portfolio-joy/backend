package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.portfolio.annotation.ValidFileType;
import com.joy.portfolio.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AboutMeDto {
	
	@JsonIgnore
	private String id;
	
	@NotBlank(message = "Name must not be empty")
	private String name;
	
	@NotBlank(message = "Description must not be empty")
	private String description;

	@NotBlank(message = "Skills must not be empty")
	private String skills;
	
	@JsonIgnore
	@ValidFileType(fileType = "image/", message = "Invalid File Type")
	private MultipartFile profile;
	
	@NotNull
	private User user;
}
