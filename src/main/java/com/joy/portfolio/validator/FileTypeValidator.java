package com.joy.portfolio.validator;

import org.springframework.web.multipart.MultipartFile;

import com.joy.portfolio.annotation.ValidFileType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<ValidFileType, MultipartFile> {
	
	private String fileType;
	
	@Override
	public void initialize(ValidFileType validFileType) {
		this.fileType = validFileType.fileType();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		return file.getContentType() != null && file.getContentType().startsWith(fileType);
	}

}
