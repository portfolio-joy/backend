package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.joy.portfolio.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, String> {

	@Modifying
	@Query("DELETE from Skill skill where skill.id=:id")
	void deleteById(@NonNull() String id);

	@Query(value = "SELECT skill.position FROM skill WHERE skill.user_id = :userId ORDER BY skill.position DESC LIMIT 1", nativeQuery = true)
	Optional<Integer> getLastOrderNumber(String userId);

	@Modifying
	@Query("UPDATE Skill skill SET skill.position = skill.position-1 WHERE skill.position >= :skillOrder AND skill.user.id = :userId")
	void updateAllProjectOrderAfterRemoval(int skillOrder, String userId);
}