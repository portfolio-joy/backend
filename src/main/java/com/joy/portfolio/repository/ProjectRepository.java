package com.joy.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joy.portfolio.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

}
