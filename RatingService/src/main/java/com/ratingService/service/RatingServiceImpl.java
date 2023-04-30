package com.ratingService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingService.model.Rating;
import com.ratingService.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	public RatingRepository ratingRepository;
	
	@Override
	public List<Rating> getRatings() {

		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {

		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Rating createRating(Rating rating) {
		 return ratingRepository.save(rating);
	}
	
	@Override
	public Rating updateRating(String id,Rating rating)
	{
		 Optional<Rating> optional_rating = ratingRepository.findById(id);
	        if (optional_rating.isPresent()) {
	            
	        	Rating updated_rating=ratingRepository.save(rating);
	        	return updated_rating;
	        	
	        }	
	        return null;
	}

	@Override
	public void deleteRating(String id) {
		
		ratingRepository.deleteById(id);
	}

}
