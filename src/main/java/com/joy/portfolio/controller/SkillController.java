package com.joy.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.SkillDto;
import com.joy.portfolio.entity.Skill;
import com.joy.portfolio.service.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class SkillController {

	@Autowired
	SkillService skillService;

	@PostMapping(value = "/skill")
	public ResponseEntity<Skill> addSkill(@RequestBody @Valid SkillDto skillDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.addSkill(skillDto));
	}

	@PutMapping(value = "/skill/{id}")
	public ResponseEntity<Skill> updateSkill(@PathVariable("id") String id, @RequestBody @Valid SkillDto skillDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.updateSkill(id, skillDto));
	}

	@DeleteMapping(value = "/skill/{id}")
	public ResponseEntity<Map<String,String>> removeSkill(@PathVariable("id") String id) {
		skillService.removeSkill(id);
		Map<String,String> response = new HashMap<String,String>();
		response.put("id", id);
		return ResponseEntity.ok(response);
	}
}
