package com.joy.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.ProjectData;

public interface ProjectDataRepository extends JpaRepository<ProjectData, String> {

	@Query("SELECT projectData FROM ProjectData projectData WHERE projectData.project.name = (SELECT project.name FROM Project project WHERE project.name=:projectName AND project.user.username=:username)")
	List<ProjectData> findByProjectNameAndUsername(String projectName, String username);
}
