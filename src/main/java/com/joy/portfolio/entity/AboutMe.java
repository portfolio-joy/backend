package com.joy.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class AboutMe {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length=600)
	private String description;

	@Column
	private String skills;
	
	@OneToOne
	@JoinColumn(name="profile_id", nullable=false)
	private Image profile;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-aboutMe")
	private User user;
}