package com.joy.portfolio.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.entity.Image;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.repository.AboutMeRepository;
import com.joy.portfolio.repository.ImageRepository;
import com.joy.portfolio.repository.UserRepository;
import com.joy.portfolio.util.ImageUtil;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AboutMeRepository aboutMeRepository;

	@Autowired
	ImageRepository imageRepository;

	public ResponseUserDto getUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid User Id"));
		ResponseUserDto responseUserDto = new ResponseUserDto(user.getId(), user.getFirstName(), user.getLastName(),
				user.getEmailId(), user.getUsername(), user.getPortfolioUrl(), user.getToken(), user.getAboutMe(),
				user.getAllSkill(), user.getAllProject(), user.getAllTestimonial(), user.getContact(),
				user.getAllSocialMedia());
		return responseUserDto;
	}

	public AboutMe addAboutMe(AboutMeDto aboutMeDto) throws IOException {
		MultipartFile profile = aboutMeDto.getProfile();
		if (!profile.getContentType().startsWith("image/")) {
			throw new IllegalArgumentException("Invalid file type : " + profile.getContentType());
		}
		Image profileImage = new Image(profile.getOriginalFilename(), profile.getContentType(),
				ImageUtil.compressImage(profile.getBytes()));
		profileImage = imageRepository.save(profileImage);
		AboutMe aboutMe = new AboutMe();
		aboutMe.setName(aboutMeDto.getName());
		aboutMe.setSkills(aboutMeDto.getSkills());
		aboutMe.setDescription(aboutMeDto.getDescription());
		aboutMe.setProfile(profileImage);
		aboutMe.setUser(aboutMeDto.getUser());
		AboutMe aboutMeFromDb = aboutMeRepository.findByUserId(aboutMeDto.getUser().getId()).orElse(null);
		if (aboutMeFromDb != null) {
			System.out.println("Reacher aboutMefromDb");
			aboutMeRepository.delete(aboutMeFromDb);
		}
		return aboutMeRepository.save(aboutMe);
	}
}
