package com.joy.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.service.UserService;

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
}
