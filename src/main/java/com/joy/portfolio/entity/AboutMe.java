package com.joy.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class AboutMe {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false, length = 35)
	private String name;

	@Lob
	@Column(nullable = false, columnDefinition = "LONGTEXT")
	private String description;

	@Column(nullable = false)
	private String skills;

	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "image_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Image", foreignKeyDefinition = "FOREIGN KEY (image_id) REFERENCES image(id) ON DELETE CASCADE"))
	private Image image;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-aboutMe")
	private User user;
}