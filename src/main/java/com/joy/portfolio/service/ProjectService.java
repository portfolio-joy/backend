package com.joy.portfolio.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.dto.ProjectDto;
import com.joy.portfolio.entity.Image;
import com.joy.portfolio.entity.Project;
import com.joy.portfolio.mapper.ProjectMapper;
import com.joy.portfolio.repository.ImageRepository;
import com.joy.portfolio.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProjectMapper projectMapper;
	
	public Project addProject(ProjectDto projectDto) throws IOException {
		if(projectRepository.existsByName(projectDto.getName(), projectDto.getUser().getId())) throw new DataIntegrityViolationException("Duplicate entry '\"+projectDto.getName()+\"' for key 'project.name'",new Throwable(""));
		MultipartFile image = projectDto.getImage();
		Image projectImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
		projectImage = imageRepository.save(projectImage);
		Project project = projectMapper.mapDtoToProject(projectDto);
		System.out.println(project);
		project.setImage(projectImage);
		return projectRepository.save(project);
	}
	
	public Project updateProject(String id, ProjectDto projectDto) throws IOException {
		if(projectRepository.existsByName(projectDto.getName(), projectDto.getUser().getId())) throw new DataIntegrityViolationException("Duplicate entry '"+projectDto.getName()+"' for key 'project.name'");
		Project project = projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Project Not Found"));
		String oldImageId = project.getImage().getId();
		MultipartFile image = projectDto.getImage();
		Image projectImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes()); 
		projectImage = imageRepository.save(projectImage);
		project = projectMapper.mapDtoToProject(projectDto);
		project.setId(id);
		project.setImage(projectImage);
		project = projectRepository.save(project);
		imageRepository.deleteById(oldImageId);
		return project;
	}
	
	public void removeProject(String id) {
		if(!projectRepository.existsById(id)) throw new EntityNotFoundException("Project Not Found");
		projectRepository.deleteById(id);
	}
}
