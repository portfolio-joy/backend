package com.joy.portfolio.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.joy.portfolio.entity.Role;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.mapper.UserMapper;
import com.joy.portfolio.repository.RoleRepository;
import com.joy.portfolio.repository.UserRepository;

@Service
public class UserAuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void register(RegisterDto registerDto) {
		if(this.userRepository.existsByEmailId(registerDto.getEmailId())) {
			throw new DataIntegrityViolationException("Duplicate entry '"+ registerDto.getEmailId() +"' for key 'user.email_id'");
		}
		if(this.userRepository.existsByUsername(registerDto.getUsername())) {
			throw new DataIntegrityViolationException("Duplicate entry '"+ registerDto.getUsername() +"' for key 'user.username'");
		}
		User user = userMapper.mapDtoToUser(registerDto);
		Role role = roleRepository.findByName("User").orElse(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPortfolioUrl(PortfolioURL.url + user.getUsername());
		user.setRole(role);
		userRepository.save(user);
	}

	public LoginResponseDto login(LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword()));
		User user = (User) authentication.getPrincipal();
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("userId", user.getId());
		extraClaims.put("roleId", user.getRole().getId());
		String jwtToken = jwtService.generateToken(extraClaims, user);
		return new LoginResponseDto("", jwtToken, user.getFirstName(), user.getUsername(), user.getRole().getName());
	}
}