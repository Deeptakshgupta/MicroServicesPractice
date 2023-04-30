package com.ratingService.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingService.model.Rating;
import com.ratingService.repository.RatingRepository;
import com.ratingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	public RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating)
	{
		Rating saved_rating= ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved_rating);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings()
	{
		List<Rating> ratings= ratingService.getRatings();
		return ResponseEntity.ok(ratings);
	}
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId)
	{
		List<Rating> ratings= ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(ratings);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId)
	{
		List<Rating> ratings= ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(ratings);
	}
	
	@PutMapping("ratings/{RatingId}")
	public ResponseEntity<Rating>  updateRating(@PathVariable String ratingId, @RequestBody Rating rating)
	{
		Rating new_rating= ratingService.updateRating(ratingId, rating);
		return ResponseEntity.ok(new_rating);
	}
	
	@DeleteMapping("ratings/{ratingId}")
	public ResponseEntity<String> deleteRating(@PathVariable String id)
	{
		ratingService.deleteRating(id);
		return ResponseEntity.ok("Deleted Successfully");
	}
}
