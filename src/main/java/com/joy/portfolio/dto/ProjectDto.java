package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.portfolio.annotation.ValidFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectDto {
	
	@NotEmpty(message = "Skill name must not be empty")
	private String name;

	@NotBlank(message = "Brief Detail must not be empty")
	@Size(min = 1,max = 300)
	private String briefDetail;

	@JsonIgnore
	@ValidFile(fileType = "image/", message = "Invalid Image Type or Image size is larger than 10 MB")
	private MultipartFile image;
}
