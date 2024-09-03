package com.joy.portfolio.dto;

import com.joy.portfolio.annotation.ValidUrl;
import com.joy.portfolio.enums.SocialMediaName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SocialMediaDto {

	@NotNull(message = "Enter Valid Social Media Name")
	@Enumerated(EnumType.STRING)
	private SocialMediaName name;

	@NotNull
	@ValidUrl
	private String url;
}
