package com.joy.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joy.portfolio.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Boolean existsByEmailId(String emailId);

	Boolean existsByUsername(String username);

	Optional<User> findByEmailId(String emailId);

	Optional<User> findByUsername(String username);

	@Query("SELECT new User(user.firstName, user.lastName, user.emailId, user.username) from User user where user.role.name <> :roleName")
	List<User> findAllButNotRole(String roleName);
	
	@Query("SELECT user.role.name from User user where user.role.id = :roleId and user.id = :id")
	Optional<String> getByIdAndRoleId(String id, String roleId);
}