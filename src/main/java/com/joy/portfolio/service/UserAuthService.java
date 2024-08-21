package com.joy.portfolio.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joy.portfolio.constants.PortfolioURL;
import com.joy.portfolio.dto.LoginDto;
import com.joy.portfolio.dto.LoginResponseDto;
import com.joy.portfolio.dto.RegisterDto;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.UserRepository;

@Service
public class UserAuthService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JWTService jwtService;

	public UserAuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public void register(RegisterDto registerDto) {
		User user = new User();
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setUsername(registerDto.getUsername());
		user.setEmailId(registerDto.getEmailId());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setPortfolioUrl(PortfolioURL.url + registerDto.getUsername());
		User userFromDb = userRepository.save(user);
		System.out.println(userFromDb);
	}

	public LoginResponseDto login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword()));
		User user = (User) authentication.getPrincipal();

		String jwtToken = jwtService.generateToken(user);
		user.setToken(jwtToken);
		userRepository.save(user);
		return new LoginResponseDto(user.getId(), jwtToken, jwtService.getExpirationTime(jwtToken));
	}
}
