package com.hotelService.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

// ApiResponse class not created like done in userService
// This is to Show that we can also handle this with already provided classes i.e. Collection
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException ex){
		
		Map<String, Object> map= new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
//		return ResponseEntity.notfound(map); // not applicable
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}
