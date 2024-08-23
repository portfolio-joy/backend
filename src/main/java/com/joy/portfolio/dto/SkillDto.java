package com.joy.portfolio.dto;

import com.joy.portfolio.entity.User;
import com.joy.portfolio.enums.SkillType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillDto {
	
	@NotEmpty(message = "Skill name must not be empty")
	private String name;

	@NotNull(message = "Enter valid skill type")
	@Enumerated(EnumType.STRING)
	private SkillType type;

	@Min(1)
	@Max(100)
	private int proficiency;
	
	@NotEmpty(message = "Description must not be empty")
	@Size(min = 1,max = 600)
	private String description;
	
	@NotNull
	private User user;
}
