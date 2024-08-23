package com.joy.portfolio.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class UserGlobalExceptionHandler {
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public @ResponseBody ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(
			DataIntegrityViolationException dataIntegrityViolationException) {
		String exceptionMessage = dataIntegrityViolationException.getRootCause().getMessage();
		Map<String, String> exceptionMap = new HashMap<>();
		if (exceptionMessage.contains("email_id")) {
			exceptionMap.put("emailId", "Email Id already exists");
		} else if (exceptionMessage.contains("username")) {
			exceptionMap.put("username", "Username already exists");
		} else {
			exceptionMap.put("general", exceptionMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMap);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
		Map<String, String> exceptionMap = new HashMap<>();
		for (FieldError fieldError : fieldErrors) {
			exceptionMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMap);
	}
	
	@ExceptionHandler(value = InternalAuthenticationServiceException.class)
	public @ResponseBody ResponseEntity<Map<String,String>> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException authenticationException) {
		Map<String,String> exceptionMap = new HashMap<>();
		System.out.println(authenticationException.getClass().getSimpleName());
		exceptionMap.put("general", authenticationException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMap);
	}
	
	@ExceptionHandler(value = AuthenticationException.class)
	public @ResponseBody ResponseEntity<Map<String,String>> handleAuthenticationException(AuthenticationException authenticationException) {
		Map<String,String> exceptionMap = new HashMap<>();
		if(authenticationException.getClass().getSimpleName().equals("BadCredentialsException"))
		{
			exceptionMap.put("general", "Invalid Credentials");
		}
		else exceptionMap.put("general", authenticationException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMap);
	}
	
	@ExceptionHandler(value = MalformedJwtException.class)
	public @ResponseBody ResponseEntity<Map<String,String>> handleMalformedJwtException(MalformedJwtException malformedJwtException) {
		Map<String,String> exceptionMap = new HashMap<>();
		exceptionMap.put("general", "Malformed JWT token");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionMap);
	}
	
	@ExceptionHandler(value = ExpiredJwtException.class)
	public @ResponseBody ResponseEntity<Map<String,String>> handleExpiredJwtException(ExpiredJwtException expiredJwtException) {
		Map<String,String> exceptionMap = new HashMap<>();
		exceptionMap.put("general", "Session Expired");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionMap);
	}
	
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseEntity<Map<String, String>> handleException(Exception exception) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("general", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMap);
	}
}
