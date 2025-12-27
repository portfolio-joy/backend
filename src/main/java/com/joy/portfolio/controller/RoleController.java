package com.joy.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joy.portfolio.dto.RoleDto;
import com.joy.portfolio.entity.Role;
import com.joy.portfolio.service.RoleService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasRole('Admin')")
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping()
	public ResponseEntity<Role> createRole(@RequestBody @Valid RoleDto roleDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Role> updateRole(@RequestBody @Valid RoleDto roleDto, @PathVariable String id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.updateRole(roleDto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> removeRole(@PathVariable String id) {
		roleService.removeRole(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("id", id);
		return ResponseEntity.ok(response);
	}
}