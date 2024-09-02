package com.joy.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.entity.ProjectData;
import com.joy.portfolio.service.JWTService;
import com.joy.portfolio.service.ProjectDataService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class ProjectDataController {
	
	@Autowired
	ProjectDataService projectDataService;
	
	@Autowired
	JWTService jwtService;
	
	@GetMapping("/portfolio/{username}/{projectName}")
	public ResponseEntity<List<ProjectData>> getByProjectNameAndUsername(@PathVariable("username") String username, @PathVariable("projectName") String projectName) {
		return ResponseEntity.ok(projectDataService.getByProjectNameAndUsername(projectName,username));
	}
	
	@GetMapping("/projectData/{projectName}")
	public ResponseEntity<List<ProjectData>> getByProjectNameAndUserId(HttpServletRequest request, @PathVariable("projectName") String projectId) {
		String username = jwtService.extractUsername(request.getHeader("Authorization").substring(7));
		return ResponseEntity.ok(projectDataService.getByProjectNameAndUsername(projectId,username));
	}
}
