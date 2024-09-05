package com.joy.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TestimonialDto {

	@NotBlank(message = "Name must not be empty")
	private String name;

	@NotNull(message = "Designation must not be empty")
	private String designation;

	@NotNull(message = "Description must not be empty")
	private String description;

	@Min(0)
	@Max(5)
	private int rating;
}
