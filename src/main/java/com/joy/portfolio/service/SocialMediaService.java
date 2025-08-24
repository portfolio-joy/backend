package com.joy.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.portfolio.dto.SocialMediaDto;
import com.joy.portfolio.entity.SocialMedia;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.exception.DataNotFoundException;
import com.joy.portfolio.mapper.SocialMediaMapper;
import com.joy.portfolio.repository.SocialMediaRepository;

import jakarta.transaction.Transactional;

@Service
public class SocialMediaService {

	@Autowired
	private SocialMediaRepository socialMediaRepository;

	@Autowired
	private SocialMediaMapper socialMediaMapper;

	public SocialMedia addSocialMedia(SocialMediaDto socialMediaDto, String userId) {
		SocialMedia socialMedia = socialMediaMapper.mapDtoToSocialMedia(socialMediaDto);
		int lastOrderNumber = socialMediaRepository.getLastOrderNumber(userId).orElse(-1);
		socialMedia.setPosition(lastOrderNumber+1);
		User user = new User();
		user.setId(userId);
		socialMedia.setUser(user);
		return socialMediaRepository.save(socialMedia);
	}

	public SocialMedia updateSocialMedia(String id, SocialMediaDto socialMediaDto, String userId) {
		if (!socialMediaRepository.existsById(id))
			throw new DataNotFoundException("Social Media Not Found");
		SocialMedia socialMedia = socialMediaMapper.mapDtoToSocialMedia(socialMediaDto);
		socialMedia.setId(id);
		User user = new User();
		user.setId(userId);
		socialMedia.setUser(user);
		return socialMediaRepository.save(socialMedia);
	}

	@Transactional
	public void removeSocialMedia(String id) {
		SocialMedia socialMedia = socialMediaRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Social Media Not Found"));
		socialMediaRepository.deleteById(id);
		socialMediaRepository.updateAllProjectOrderAfterRemoval(socialMedia.getPosition(),
				socialMedia.getUser().getId());
	}
}