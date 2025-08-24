package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@Query("SELECT project from Project project WHERE project.id = :id AND project.user.username = :username")
	Optional<Project> findByIdAndUsername(String id, String username);

	@Query(value = "SELECT project.position FROM project WHERE project.user_id = :userId ORDER BY project.position DESC LIMIT 1", nativeQuery = true)
	Optional<Integer> getLastOrderNumber(String userId);

	@Modifying
	@Query("UPDATE Project project SET project.position = project.position-1 WHERE project.position >= :projectOrder AND project.user.id = :userId")
	void updateAllProjectOrderAfterRemoval(int projectOrder, String userId);

}