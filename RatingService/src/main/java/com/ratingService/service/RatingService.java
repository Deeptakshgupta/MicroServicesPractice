package com.ratingService.service;

import java.util.List;

import com.ratingService.model.Rating;

public interface RatingService {

	public Rating createRating(Rating rating);
	
	public List<Rating> getRatings();
	
	public List<Rating> getRatingByUserId(String id);
	
	public List<Rating> getRatingByHotelId(String id);
	
	public Rating updateRating(String id, Rating rating); 
	
	public void deleteRating (String id); 
}
