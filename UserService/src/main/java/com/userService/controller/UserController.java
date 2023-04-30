package com.userService.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userService.model.Micro_User;
import com.userService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Micro_User> createUser(@RequestBody Micro_User user)
	{
		// Providing Random ID's 
		Micro_User saved_user=userService.saveUser(user);
		// way 1 to send response
		return ResponseEntity.ok(saved_user);
		// way 2. to send the response
//		return new ResponseEntity<>(saved_user,HttpStatus.OK);
		// way 3. pto send the response
//		else we can also return response entity as
		//-> new ResponseEntity.status(HttpStatus.).body();
	}
	
	int retry=0;
	// as parameter name and pathVaribale names are same so no need to write like
	//@PathVariable(name = "userId"), it will automatically map that with the parameter
	
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
//	@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<Micro_User> getSinlgeUser(@PathVariable String userId) 
	{
		retry++;
		log.info("Retry Count{}:"+retry);
		log.info("Get  Single User Handler");
		Micro_User user= userService.getUser(userId);
		
		return ResponseEntity.ok(user);
	}
	
//	creating fall back method for circuit breaker,same return type as Service Return type whose fallback method is being made
	public ResponseEntity<Micro_User> ratingHotelFallback(String userId, Exception ex)

	{
		log.info("Fallback is executed because service is down:", ex.getMessage() );
		Micro_User user=Micro_User.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This user is created dummy beacuse Some service was Down")
		.userId("141234")
		.build();
		return new ResponseEntity<>(user,HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}  
	@GetMapping
	public ResponseEntity<List<Micro_User>> getAllUsers()
	{
		List<Micro_User> users= userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
}
