package com.joy.portfolio.validator;

import com.joy.portfolio.annotation.ValidUrl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.UrlValidator;

public class URLValidator implements ConstraintValidator<ValidUrl, String> {

	private static final UrlValidator URL_VALIDATOR = new UrlValidator();

	@Override
	public void initialize(ValidUrl validUrl) {
	}

	@Override
	public boolean isValid(String url, ConstraintValidatorContext context) {
		return URL_VALIDATOR.isValid(url);
	}

}
