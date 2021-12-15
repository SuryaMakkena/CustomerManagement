package com.usecase.CustomerManagement.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usecase.CustomerManagement.Exceptions.UserNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	public static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception ex) {
		LOG.info("Handling User not found Exception");
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<String> handleDataIntegrityViolationException(Exception ex) {
		LOG.info("Handling DataIntegrityViolationException");
		return new ResponseEntity<String>("The inputs you entered are not valid. Please check whether there is any null value",HttpStatus.BAD_REQUEST);
	}

}
