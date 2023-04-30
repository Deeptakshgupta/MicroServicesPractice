package com.userService.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.userService.model.Rating;

@FeignClient(name= "RATING-SERVICE")
public interface RatingService {
// By default all methods on interface are public
	
	//get
	@GetMapping("ratings/users/{userId}")
	public ResponseEntity<List<Rating>> getRating(@PathVariable String userId);
	
	
	// post
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(Rating ratings);
	
	// put for Updation Methods
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
	
	
	//Delete
	@DeleteMapping("ratings/{ratingId}")
	public ResponseEntity<String>deleteRating(@PathVariable String ratingId);
}
