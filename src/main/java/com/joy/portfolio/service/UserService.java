package com.joy.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<ResponseUserDto> getUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid User Id"));
		ResponseUserDto responseUserDto = new ResponseUserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmailId(),user.getUsername(),user.getPortfolioUrl(),user.getToken(),user.getAboutMe(),user.getAllSkill(),user.getAllProject(),user.getAllTestimonial(),user.getContact(),user.getAllSocialMedia());
		return ResponseEntity.ok(responseUserDto);
	}
}
