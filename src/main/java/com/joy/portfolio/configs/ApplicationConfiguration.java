package com.joy.portfolio.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.UserRepository;

@Configuration
public class ApplicationConfiguration {

	private final UserRepository userRepository;

	public ApplicationConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	UserDetailsService userDetailService() {
		return loginId ->{
			User user =  userRepository.findByUsername(loginId)
				.orElse(userRepository.findByEmailId(loginId).orElse(null));
			if(user == null) throw new BadCredentialsException("Invalid credentials");
			return user;
		};
	}
}
