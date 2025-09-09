package com.joy.portfolio.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.joy.portfolio.entity.Role;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.RoleRepository;
import com.joy.portfolio.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Value("${joy.portfolio.admin.name}")
	private String adminName;
	
	@Value("${joy.portfolio.admin.portfolio}")
	private String adminPortfolio;
	
	@Value("${joy.portfolio.admin.username}")
	private String adminUsername;
	
	@Value("${joy.portfolio.admin.password}")
	private String adminPassword;
	
	@Value("${joy.portfolio.admin.email}")
	private String adminEmail;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Control");
		
		// 1. Ensure if Roles already exists
		Role userRole = roleRepository.findByName("User").orElseGet(() -> roleRepository.save(new Role("User")));
		Role adminRole = roleRepository.findByName("Admin").orElseGet(() -> roleRepository.save(new Role("Admin")));
		
		// 2. Update all the existing user roles
		userRepository.findAll().forEach(user -> {
            if (user.getRole() == null) {
                user.setRole(userRole);
                userRepository.save(user);
            }
        });
		
		// 3. Create a Admin User
		if (!userRepository.existsByUsername(adminUsername)) {
            User admin = new User();
            admin.setFirstName(adminName);
            admin.setUsername(adminUsername);
            admin.setEmailId(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setPortfolioUrl(adminPortfolio);
            admin.setRole(adminRole);
            userRepository.save(admin);
            System.out.println("Admin user created.");
        }
	}

}
