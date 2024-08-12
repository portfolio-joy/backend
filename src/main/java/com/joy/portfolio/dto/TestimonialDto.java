package com.joy.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TestimonialDto {
	
	@NotNull
	private String name;

	@NotNull
	private String designation;

	@NotNull
	private String description;

	@NotNull
	@Min(1)
	@Max(5)
	private int rating;
}
