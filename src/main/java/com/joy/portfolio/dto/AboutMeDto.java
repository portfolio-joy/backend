package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AboutMeDto {
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;

	@NotNull
	private String skills;
	
	private MultipartFile profile;
	
	@NotNull
	private User user;
}
