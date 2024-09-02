package com.joy.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.portfolio.entity.ProjectData;
import com.joy.portfolio.repository.ProjectDataRepository;
import com.joy.portfolio.repository.ProjectRepository;

@Service
public class ProjectDataService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectDataRepository projectDataRepository;

	public List<ProjectData> getByProjectNameAndUsername(String projectName, String username) {
		return projectDataRepository.findByProjectNameAndUsername(projectName,username);
	}
}
