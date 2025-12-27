package com.joy.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joy.portfolio.dto.ResponseRoleDto;
import com.joy.portfolio.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	Optional<Role> findByName(String name);
	
	@Query("SELECT new com.joy.portfolio.dto.ResponseRoleDto(role.name, COUNT(user.id)) from Role role LEFT JOIN User user ON role = user.role GROUP BY role.name ORDER BY role.name")
	List<ResponseRoleDto> findAllByRoleAndUser();
}