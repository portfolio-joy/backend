package com.joy.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.portfolio.annotation.ValidFile;
import com.joy.portfolio.entity.Project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectDataDto {

	@NotBlank(message = "Heading must not be empty")
	private String heading;

	@NotBlank(message = "Description name must not be empty")
	private String description;

	@JsonIgnore
	@ValidFile(fileType = "image/", message = "Invalid Image Type or Image size is larger than 10 MB")
	private MultipartFile image;

	@NotNull
	private Project project;
}
