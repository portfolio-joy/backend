package com.joy.portfolio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.service.AboutMeService;
import com.joy.portfolio.validator.DtoValidator;


@RestController
@RequestMapping("/user")
public class AboutMeController {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	DtoValidator dtoValidator;
	
	@Autowired
	AboutMeService aboutMeService;

	@PostMapping(value = "/aboutMe", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> addAboutMe(@RequestPart String aboutMeData,
			@RequestPart("profile") MultipartFile profile) throws IOException {
		AboutMeDto aboutMeDto = objectMapper.readValue(aboutMeData, AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		dtoValidator.validate(aboutMeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(aboutMeService.addAboutMe(aboutMeDto));
	}

	@PutMapping(value = "/aboutMe/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> updateAboutMe(@PathVariable("id") String id, @RequestPart String aboutMeData,
			@RequestPart("profile") MultipartFile profile) throws IOException {
		AboutMeDto aboutMeDto = objectMapper.readValue(aboutMeData, AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		dtoValidator.validate(aboutMeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(aboutMeService.updateAboutMe(id, aboutMeDto));
	}
}
