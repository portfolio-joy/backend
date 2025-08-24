package com.joy.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.ProjectData;

public interface ProjectDataRepository extends JpaRepository<ProjectData, String> {

	@Query("SELECT projectData FROM ProjectData projectData WHERE projectData.project.id = :projectId ORDER BY projectData.position")
	List<ProjectData> findByProjectId(String projectId);

	@Query(value = "SELECT projectData.position FROM project_data projectData WHERE projectData.project_id = :projectId ORDER BY projectData.position DESC LIMIT 1", nativeQuery = true)
	Optional<Integer> getLastOrderNumber(String projectId);

	@Modifying
	@Query("UPDATE ProjectData projectData SET projectData.position = :projectDataOrder WHERE projectData.id = :id")
	void updateSingleProjectDataOrder(int projectDataOrder, String id);

	@Modifying
	@Query("UPDATE ProjectData projectData "
			+ "SET projectData.position = "
			+ "CASE "
			+ "WHEN projectData.position >= :newOrderNumber AND projectData.position < :oldOrderNumber THEN projectData.position+1 "
			+ "WHEN projectData.position <= :newOrderNumber AND projectData.position > :oldOrderNumber THEN projectData.position-1  "
			+ "ELSE projectData.position "
			+ "END "
			+ "WHERE projectData.id != :id AND projectData.project.id = :projectId")
	void updateAllProjectDataOrderBeforeReordering(int oldOrderNumber, int newOrderNumber, String id, String projectId);

	@Modifying
	@Query("UPDATE ProjectData projectData SET projectData.position = projectData.position-1 WHERE projectData.position >= :projectDataOrder AND projectData.project.id = :projectId")
	void updateAllProjectDataOrderAfterRemoval(int projectDataOrder, String projectId);
}