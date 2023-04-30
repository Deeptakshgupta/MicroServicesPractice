/**
 * 
 */
package com.userService.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userService.payLoad.ApiResponse;

// using this we are making this class as the 
// Centralized exception handler for the whole class
@RestControllerAdvice
public class GlobalExceptionHandler {

	// Making this mthd to handle any Exception thrown w.r.t the class passed as the exception in this annotation  
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message= ex.getMessage();
		ApiResponse response=ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		
		// succes status was true, as per the video
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
				
	}
	
}
