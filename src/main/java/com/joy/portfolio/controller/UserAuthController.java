package com.joy.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.LoginDto;
import com.joy.portfolio.dto.LoginResponseDto;
import com.joy.portfolio.dto.RegisterDto;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.service.UserAuthService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class UserAuthController {

	@Autowired
	private UserAuthService userAuthService;

	@PostMapping("/register")
	@Transactional
	public @ResponseBody ResponseEntity<ResponseUserDto> register(@Valid @RequestBody RegisterDto registerDto) {
		System.out.println(registerDto);
		userAuthService.register(registerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
		return ResponseEntity.ok(userAuthService.login(loginDto));
	}
}
