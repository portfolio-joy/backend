package com.joy.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.joy.portfolio.enums.SkillType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SkillType type;

	@Column(nullable = false, columnDefinition = "Integer default '1'")
	@Min(1)
	@Max(100)
	private int proficiency;
	
	@Column(nullable = false, length=600)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-skill")
	private User user;
	
	public Skill(String name, SkillType skillType, int proficiency, String description, User user) {
		this.name = name;
		this.type = skillType;
		this.proficiency = proficiency;
		this.user = user;
	}
}