package com.ratingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratingService.model.Rating;

// for MongoDb
// public interface RatingRepository extends MongoRepository<Rating,>

// we can't use @Repository over the interface and when we Use this in Service class implementation a proxy object is made which is then Autowired

public interface RatingRepository extends JpaRepository<Rating,String> {
   
	
	//custom Methods
	
	// findBy(Camel case member variable name) and then ignore case if required 
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
