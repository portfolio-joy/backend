package com.joy.portfolio.dto;

import com.joy.portfolio.enums.SkillType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SkillDto {
	
	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private SkillType type;

	@NotNull
	@Min(1)
	@Max(100)
	private int proficiency;
}
