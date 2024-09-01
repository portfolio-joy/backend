package com.joy.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, length=300)
	private String briefDetail;
	
	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="image_id")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-project")
	private User user;

//	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<ProjectData> projectData = new ArrayList<>();
}