package com.joy.portfolio.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joy.portfolio.constants.PortfolioURL;
import com.joy.portfolio.dto.RegisterDto;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.UserRepository;

@Service
public class UserAuthService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public UserAuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User register(RegisterDto registerDto) {
		System.out.println("Reached User service");
		User user = new User();
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setUsername(registerDto.getUsername());
		user.setEmailId(registerDto.getEmailId());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setPortfolioUrl(PortfolioURL.url + registerDto.getUsername());
		return userRepository.save(user);
	}
}
