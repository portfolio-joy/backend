package com.joy.portfolio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.dto.SkillDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.entity.Skill;
import com.joy.portfolio.service.UserService;
import com.joy.portfolio.validator.DtoValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	DtoValidator dtoValidator;

	@GetMapping("{id}")
	public ResponseEntity<ResponseUserDto> getUser(@PathVariable("id") String id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@GetMapping("/portfolio/{username}")
	public ResponseEntity<ResponseUserDto> getUserFromUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.getUserFromUsername(username));
	}

	@PostMapping(value = "/aboutMe", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> addAboutMe(@RequestPart String aboutMeData,
			@RequestPart("profile") MultipartFile profile)
			throws IOException {
		AboutMeDto aboutMeDto = objectMapper.readValue(aboutMeData, AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		dtoValidator.validate(aboutMeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAboutMe(aboutMeDto));
	}

	@PutMapping(value = "/aboutMe/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AboutMe> updateAboutMe(@PathVariable("id") String id, @RequestPart String aboutMeData,
			@RequestPart("profile")  MultipartFile profile)
			throws IOException {
		AboutMeDto aboutMeDto = objectMapper.readValue(aboutMeData, AboutMeDto.class);
		aboutMeDto.setProfile(profile);
		dtoValidator.validate(aboutMeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateAboutMe(id, aboutMeDto));
	}

	@PostMapping(value = "/skill")
	public ResponseEntity<Skill> addSkill(@RequestBody @Valid SkillDto skillDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addSkill(skillDto));
	}

	@PutMapping(value = "/skill/{id}")
	public ResponseEntity<Skill> updateSkill(@PathVariable("id") String id, @RequestBody @Valid SkillDto skillDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateSkill(id, skillDto));
	}

	@DeleteMapping(value = "/skill/{id}")
	public ResponseEntity<String> removeSkill(@PathVariable("id") String id) {
		userService.removeSkill(id);
		return ResponseEntity.ok(id);
	}
}
