package com.joy.portfolio.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.joy.portfolio.controller.AboutMeController;
import com.joy.portfolio.controller.ContactController;
import com.joy.portfolio.controller.ProjectController;
import com.joy.portfolio.controller.ProjectDataController;
import com.joy.portfolio.controller.SkillController;
import com.joy.portfolio.controller.SocialMediaController;
import com.joy.portfolio.controller.TestimonialController;
import com.joy.portfolio.controller.UserController;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.entity.Contact;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.filters.JWTAuthenticationFilter;
import com.joy.portfolio.filters.UserRoleFilter;
import com.joy.portfolio.repository.AboutMeRepository;
import com.joy.portfolio.repository.ContactRepository;
import com.joy.portfolio.repository.ProjectDataRepository;
import com.joy.portfolio.repository.ProjectRepository;
import com.joy.portfolio.repository.SkillRepository;
import com.joy.portfolio.repository.SocialMediaRepository;
import com.joy.portfolio.repository.TestimonialRepository;
import com.joy.portfolio.repository.UserRepository;
import com.joy.portfolio.service.AboutMeService;
import com.joy.portfolio.service.ContactService;
import com.joy.portfolio.service.JWTService;
import com.joy.portfolio.service.ProjectDataService;
import com.joy.portfolio.service.ProjectService;
import com.joy.portfolio.service.SkillService;
import com.joy.portfolio.service.SocialMediaService;
import com.joy.portfolio.service.TestimonialService;
import com.joy.portfolio.service.UserService;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private JWTService jwtService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	@MockBean
	private UserRoleFilter userRoleFilter;

	@MockBean
	private AboutMeController aboutMeController;

	@MockBean
	private SkillController skillController;

	@MockBean
	private ProjectController projectController;

	@MockBean
	private ProjectDataController projectDataController;

	@MockBean
	private TestimonialController testimonialController;

	@MockBean
	private SocialMediaController socialMediaController;

	@MockBean
	private ContactController contactController;

	@MockBean
	private AboutMeService aboutMeService;

	@MockBean
	private SkillService skillService;

	@MockBean
	private ProjectService projectService;

	@MockBean
	private ProjectDataService projectDataService;

	@MockBean
	private TestimonialService testimonialService;

	@MockBean
	private SocialMediaService socialMediaService;

	@MockBean
	private ContactService contactService;

	@MockBean
	private AboutMeRepository aboutMeRepository;

	@MockBean
	private SkillRepository skillRepository;

	@MockBean
	private ProjectRepository projectRepository;

	@MockBean
	private ProjectDataRepository projectDataRepository;

	@MockBean
	private TestimonialRepository testimonialRepository;

	@MockBean
	private SocialMediaRepository socialMediaRepository;

	@MockBean
	private ContactRepository contactRepository;

	@Test
	void getUserTest() throws Exception {
		ResponseUserDto responseUserDto = new ResponseUserDto("1234", "Test", "User", "test123@example.com", "test123",
				"test123.com", new AboutMe(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Contact(),
				new ArrayList<>());
		when(jwtService.extractUserId(any())).thenReturn("1234");
		when(jwtService.extractUsername(any())).thenReturn("test123");
		when(userService.getUser("1234")).thenReturn(responseUserDto);
		when(userRepository.findByUsername("test123")).thenReturn(Optional.of(new User()));
		this.mockMvc.perform(get("/user").header("Authorization", "Bearer dummy-token")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1234"));
	}
}
