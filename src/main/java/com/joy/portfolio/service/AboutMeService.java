package com.joy.portfolio.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.entity.Image;
import com.joy.portfolio.mapper.AboutMeMapper;
import com.joy.portfolio.repository.AboutMeRepository;
import com.joy.portfolio.repository.ImageRepository;

@Service
public class AboutMeService {

	@Autowired
	AboutMeRepository aboutMeRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	AboutMeMapper aboutMeMapper;

	public AboutMe addAboutMe(AboutMeDto aboutMeDto) throws IOException {
		MultipartFile profile = aboutMeDto.getProfile();
		Image profileImage = new Image(profile.getOriginalFilename(), profile.getContentType(), profile.getBytes());
		profileImage = imageRepository.save(profileImage);
		AboutMe aboutMe = aboutMeMapper.mapDtoToAboutMe(aboutMeDto);
		aboutMe.setProfile(profileImage);
		return aboutMeRepository.save(aboutMe);
	}

	public AboutMe updateAboutMe(String id, AboutMeDto aboutMeDto) throws IOException {
		AboutMe aboutMe = aboutMeRepository.findById(id).orElse(null);
		String oldProfileId = aboutMe.getProfile().getId();
		MultipartFile profile = aboutMeDto.getProfile();
		Image profileImage = new Image(profile.getOriginalFilename(), profile.getContentType(), profile.getBytes());
		profileImage = imageRepository.save(profileImage);
		aboutMe = aboutMeMapper.mapDtoToAboutMe(aboutMeDto);
		aboutMe.setId(id);
		aboutMe.setProfile(profileImage);
		aboutMe = aboutMeRepository.save(aboutMe);
		imageRepository.deleteById(oldProfileId);
		return aboutMe;
	}
}
