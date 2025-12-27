package com.joy.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.AdminResponseDto;
import com.joy.portfolio.dto.ResponseRoleDto;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.service.RoleService;
import com.joy.portfolio.service.UserService;

@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/all")
	public ResponseEntity<AdminResponseDto> getAllAdminData() {
		List<User> users =  userService.getAllUsers();
		List<ResponseRoleDto> roles = roleService.getAllRoles();
		return ResponseEntity.ok(new AdminResponseDto(users, roles));
	}
}