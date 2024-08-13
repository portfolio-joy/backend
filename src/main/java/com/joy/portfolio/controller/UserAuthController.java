package com.joy.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.RegisterDto;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.service.UserAuthService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class UserAuthController {
    
	@Autowired
    private UserAuthService userAuthService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
        User registeredUser = userAuthService.register(registerDto);

        return ResponseEntity.ok(registeredUser);
    }
}
