package com.joy.portfolio.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.joy.portfolio.filters.JWTAuthenticationFilter;
import com.joy.portfolio.filters.UserRoleFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final AuthenticationProvider authenticationProvider;
	private final CorsConfigurationSource corsConfigurationSource;
	private final JWTAuthenticationFilter jwtAuthenticationFilter;
	private final UserRoleFilter userRoleFilter;

	public SecurityConfiguration(JWTAuthenticationFilter jwtAuthenticationFilter,
			AuthenticationProvider authenticationProvider, CorsConfigurationSource corsConfigurationSource,
			UserRoleFilter userRoleFilter) {
		this.authenticationProvider = authenticationProvider;
		this.corsConfigurationSource = corsConfigurationSource;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userRoleFilter = userRoleFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource)).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/v3/api-docs/**").permitAll()
						.requestMatchers("/user/portfolio/**").permitAll().requestMatchers("/actuator/**").permitAll().anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(userRoleFilter, JWTAuthenticationFilter.class);
		return http.build();
	}
}
