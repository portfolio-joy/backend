package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joy.portfolio.entity.AboutMe;

public interface AboutMeRepository extends JpaRepository<AboutMe, String> {

	@Query("SELECT aboutMe from AboutMe aboutMe where aboutMe.user.id=:id")
	Optional<AboutMe> findByUserId(String id);
}
