package com.joy.portfolio.dto;

import com.joy.portfolio.annotation.ValidUrl;
import com.joy.portfolio.enums.SocialMediaName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class SocialMediaDto {

	@NotNull
	@Enumerated(EnumType.STRING)
	private SocialMediaName name;

	@NotNull
	@ValidUrl
	private String url;
}
