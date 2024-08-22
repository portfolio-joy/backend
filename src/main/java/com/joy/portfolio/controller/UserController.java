package com.joy.portfolio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseUserDto> getUser(@PathVariable("id") String id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@GetMapping("/portfolio/{username}")
	public ResponseEntity<ResponseUserDto> getUserFromUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.getUserFromUsername(username));
	}
	
	@PostMapping(value="/aboutMe",consumes={ MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> addAboutMe(@RequestPart @Valid String aboutMeData, @Valid @RequestPart("profile") MultipartFile profile) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		AboutMeDto aboutMeDto = new AboutMeDto();
		aboutMeDto = objectMapper.readValue(aboutMeData,AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAboutMe(aboutMeDto));
	}
	
	@PutMapping(value="/aboutMe/{id}",consumes={ MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> updateAboutMe(@PathVariable("id") String id, @RequestPart @Valid String aboutMeData, @RequestPart("profile") MultipartFile profile) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		AboutMeDto aboutMeDto = new AboutMeDto();
		aboutMeDto = objectMapper.readValue(aboutMeData,AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateAboutMe(id, aboutMeDto));
	}
}
