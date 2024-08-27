package com.joy.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.portfolio.dto.SkillDto;
import com.joy.portfolio.entity.Skill;
import com.joy.portfolio.mapper.SkillMapper;
import com.joy.portfolio.repository.SkillRepository;

@Service
public class SkillService {
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	SkillMapper skillMapper;
	
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