package com.joy.portfolio.validator;

import com.joy.portfolio.annotation.ValidUrl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<ValidUrl, String> {

	private static final URLValidator URL_VALIDATOR = new URLValidator();

	@Override
	public void initialize(ValidUrl validUrl) {
	}

	@Override
	public boolean isValid(String url, ConstraintValidatorContext context) {
		return URL_VALIDATOR.isValid(url,context);
	}

}
