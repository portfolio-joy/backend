package com.joy.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.portfolio.dto.ResponseRoleDto;
import com.joy.portfolio.dto.RoleDto;
import com.joy.portfolio.entity.Role;
import com.joy.portfolio.exception.DataNotFoundException;
import com.joy.portfolio.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role createRole(RoleDto roleDto) {
		Role role = new Role(roleDto.getName());
		return roleRepository.save(role);
	}

	public Role updateRole(RoleDto roleDto, String id) {
		if (!roleRepository.existsById(id))
			throw new DataNotFoundException("Role not found");
		Role role = new Role(roleDto.getName());
		role.setId(id);
		return roleRepository.save(role);
	}

	public void removeRole(String id) {
		if (!roleRepository.existsById(id))
			throw new DataNotFoundException("Role not found");
		roleRepository.deleteById(id);
	}
	
	public List<ResponseRoleDto> getAllRoles() {
		return roleRepository.findAllByRoleAndUser();
	}
}