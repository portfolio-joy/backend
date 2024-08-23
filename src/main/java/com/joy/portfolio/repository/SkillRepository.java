package com.joy.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joy.portfolio.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, String> {

}
