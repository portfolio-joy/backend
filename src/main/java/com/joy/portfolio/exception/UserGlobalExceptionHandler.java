package com.joy.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class UserGlobalExceptionHandler {
	
	@ExceptionHandler(value = ConstraintViolationException.class) 
	public @ResponseBody ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException constraintViolationException){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constraintViolationException.getMessage().split("\\r")[0]);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public @ResponseBody ResponseEntity<String> handleValidationException(ValidationException validationException) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationException.getMessage().split("\\r")[0]);
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public @ResponseBody ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage().split("\\r")[0]);
	}
	
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseEntity<String> handleException(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage().split("\\r")[0]);
	}
}
