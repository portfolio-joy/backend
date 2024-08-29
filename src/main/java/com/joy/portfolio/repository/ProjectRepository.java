package com.joy.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@Query("SELECT COUNT(p)>0 FROM Project p WHERE p.name=:name AND p.user.id=:id")
	boolean existsByName(String name, String id);

}
