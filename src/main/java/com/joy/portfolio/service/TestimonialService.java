package com.joy.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.portfolio.dto.TestimonialDto;
import com.joy.portfolio.entity.Testimonial;
import com.joy.portfolio.entity.User;
import com.joy.portfolio.exception.DataNotFoundException;
import com.joy.portfolio.mapper.TestimonialMapper;
import com.joy.portfolio.repository.TestimonialRepository;

import jakarta.transaction.Transactional;

@Service
public class TestimonialService {

	@Autowired
	private TestimonialRepository testimonialRepository;

	@Autowired
	private TestimonialMapper testimonialMapper;

	public Testimonial addTestimonial(TestimonialDto testimonialDto, String userId) {
		Testimonial testimonial = testimonialMapper.mapDtoToTestimonial(testimonialDto);
		int lastOrderNumber = testimonialRepository.getLastOrderNumber(userId).orElse(-1);
		testimonial.setPosition(lastOrderNumber+1);
		User user = new User();
		user.setId(userId);
		testimonial.setUser(user);
		return testimonialRepository.save(testimonial);
	}

	public Testimonial updateTestimonial(String id, TestimonialDto testimonialDto, String userId) {
		if (!testimonialRepository.existsById(id))
			throw new DataNotFoundException("Testimonial not found");
		Testimonial testimonial = testimonialMapper.mapDtoToTestimonial(testimonialDto);
		testimonial.setId(id);
		User user = new User();
		user.setId(userId);
		testimonial.setUser(user);
		return testimonialRepository.save(testimonial);
	}

	@Transactional
	public void removeTestimonial(String id) {
		Testimonial testimonial = testimonialRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Testimonial Not Found"));
		testimonialRepository.deleteById(id);
		testimonialRepository.updateAllProjectOrderAfterRemoval(testimonial.getPosition(),
				testimonial.getUser().getId());
	}
}