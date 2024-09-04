package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@Query("SELECT COUNT(project)>0 FROM Project project WHERE project.name=:name AND project.user.id=:userId")
	boolean existsByName(String name, String userId);

}
