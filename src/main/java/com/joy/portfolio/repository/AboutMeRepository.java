package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joy.portfolio.entity.AboutMe;

public interface AboutMeRepository extends JpaRepository<AboutMe, String> {

    Optional<AboutMe> findByIdAndUserId(String id, String userId);
}