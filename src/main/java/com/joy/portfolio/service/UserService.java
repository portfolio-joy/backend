package com.joy.portfolio.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.dto.AboutMeDto;
import com.joy.portfolio.dto.ResponseUserDto;
import com.joy.portfolio.dto.SkillDto;
import com.joy.portfolio.entity.AboutMe;
import com.joy.portfolio.entity.Image;
import com.joy.portfolio.entity.Skill;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.exception.UserNotFoundException;
import com.joy.portfolio.mapper.AboutMeMapper;
import com.joy.portfolio.mapper.SkillMapper;
import com.joy.portfolio.mapper.UserMapper;
import com.joy.portfolio.repository.AboutMeRepository;
import com.joy.portfolio.repository.ImageRepository;
import com.joy.portfolio.repository.SkillRepository;
import com.joy.portfolio.repository.UserRepository;
import com.joy.portfolio.util.ImageUtil;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AboutMeRepository aboutMeRepository;
	
	@Autowired
	SkillRepository skillRepository;

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AboutMeMapper aboutMeMapper;
	
	@Autowired
	SkillMapper skillMapper;

	public ResponseUserDto getUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid User Id"));
		ResponseUserDto responseUserDto = userMapper.mapUserToDto(user);
		return responseUserDto;
	}

	public ResponseUserDto getUserFromUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Invalid username"));
		ResponseUserDto responseUserDto = userMapper.mapUserToDto(user);
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
		AboutMe aboutMe = aboutMeMapper.mapDtoToAboutMe(aboutMeDto);
		aboutMe.setProfile(profileImage);
		return aboutMeRepository.save(aboutMe);
	}

	public AboutMe updateAboutMe(String id, AboutMeDto aboutMeDto) throws IOException {
		AboutMe aboutMe = aboutMeRepository.findById(id).orElse(null);
		String oldProfileId = aboutMe.getProfile().getId();
		MultipartFile profile = aboutMeDto.getProfile();
		Image profileImage = new Image(profile.getOriginalFilename(), profile.getContentType(),
				ImageUtil.compressImage(profile.getBytes()));
		profileImage = imageRepository.save(profileImage);
		profileImage.setImageData(ImageUtil.decompressImage(profileImage.getImageData()));
		aboutMe = aboutMeMapper.mapDtoToAboutMe(aboutMeDto);
		aboutMe.setId(id);
		aboutMe.setProfile(profileImage);
		aboutMe.setUser(userRepository.findById(aboutMeDto.getUser().getId()).orElse(null));
		aboutMe = aboutMeRepository.save(aboutMe);
		imageRepository.deleteById(oldProfileId);
		return aboutMe;
	}

	public Skill addSkill(SkillDto skillDto) {
		Skill skill = skillMapper.mapDtoToSkill(skillDto);
		return skillRepository.save(skill);
	}
	
	public Skill updateSkill(String id, SkillDto skillDto) {
		Skill skill = skillMapper.mapDtoToSkill(skillDto);
		skill.setId(id);
		return skillRepository.save(skill);
	}
	
	public void removeSkill(String id) {
		skillRepository.deleteById(id);
	}
}
