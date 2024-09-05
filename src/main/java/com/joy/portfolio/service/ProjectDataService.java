package com.joy.portfolio.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.dto.ProjectDataDto;
import com.joy.portfolio.entity.Image;
import com.joy.portfolio.entity.ProjectData;
import com.joy.portfolio.exception.DataNotFoundException;
import com.joy.portfolio.mapper.ProjectDataMapper;
import com.joy.portfolio.repository.ImageRepository;
import com.joy.portfolio.repository.ProjectDataRepository;
import com.joy.portfolio.repository.ProjectRepository;

@Service
public class ProjectDataService {

	@Autowired
	ProjectDataRepository projectDataRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectDataMapper projectDataMapper;

	public List<ProjectData> getByProjectNameAndUsername(String projectName, String username) {
		return projectDataRepository.findByProjectNameAndUsername(projectName, username);
	}

	public ProjectData addProjectData(ProjectDataDto projectDataDto) throws IOException {
		MultipartFile image = projectDataDto.getImage();
		Image projectDataImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
		projectDataImage = imageRepository.save(projectDataImage);
		ProjectData projectData = projectDataMapper.mapDtoToProjectData(projectDataDto);
		projectData.setImage(projectDataImage);
		return projectDataRepository.save(projectData);
	}

	public ProjectData updateProjectData(String id, ProjectDataDto projectDataDto) throws IOException {
		ProjectData projectData = projectDataRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Project Data Not Found"));
		String oldImageId = projectData.getImage().getId();
		MultipartFile image = projectDataDto.getImage();
		Image projectDataImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
		projectDataImage = imageRepository.save(projectDataImage);
		projectData = projectDataMapper.mapDtoToProjectData(projectDataDto);
		projectData.setId(id);
		projectData.setImage(projectDataImage);
		projectData = projectDataRepository.save(projectData);
		imageRepository.deleteById(oldImageId);
		return projectData;
	}

	public void removeProjectData(String id) {
		if (!projectDataRepository.existsById(id))
			throw new DataNotFoundException("Project Data Not Found");
		projectDataRepository.deleteById(id);
	}
}