package com.server.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.server.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// ---------------- LOGGER ----------------

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// ---------------- RESOURCE NOT FOUND ----------------

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleResourceNotFound(ResourceNotFoundException exception) {

		logger.warn("Resource Not Found : {}", exception.getMessage());

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Resource Not Found");
		response.setData(exception.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// ---------------- DUPLICATE RESOURCE ----------------

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateResource(DuplicateResourceException exception) {

		logger.warn("Duplicate Resource : {}", exception.getMessage());

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.CONFLICT.value());
		response.setMessage("Duplicate Resource");
		response.setData(exception.getMessage());

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	// ---------------- INVALID CREDENTIALS ----------------

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidCredentials(InvalidCredentialsException exception) {

		logger.warn("Invalid Credentials : {}", exception.getMessage());

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setMessage("Invalid Credentials");
		response.setData(exception.getMessage());

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	// ---------------- VALIDATION EXCEPTION ----------------

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationException(
			MethodArgumentNotValidException exception) {

		logger.warn("Validation Failed");

		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ResponseStructure<Map<String, String>> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Validation Failed");
		response.setData(errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// ---------------- INTERNAL SERVER ERROR ----------------

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handleException(Exception exception) {

		logger.error("Unexpected Exception Occurred", exception);

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Internal Server Error");
		response.setData("Something went wrong. Please contact the administrator.");

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}