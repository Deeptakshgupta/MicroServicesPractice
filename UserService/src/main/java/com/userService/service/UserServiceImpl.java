package com.userService.service;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userService.exceptionHandler.ResourceNotFoundException;
import com.userService.externalService.HotelService;
import com.userService.externalService.RatingService;
import com.userService.model.Hotel;
import com.userService.model.Micro_User;
import com.userService.model.Rating;
import com.userService.repository.UserRepositroy;

//import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

//@Slf4j provides us with the log object  to work with the logs

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired 
	public UserRepositroy userRepository;
	
	@Autowired
	public RestTemplate restTemplate;
	// No beans of RestTemplate Found, hence can't be Autowired -> According to the video, Code by Durgesh
	// hence we have to declare the Bean Explicitly, According to the video
	// but wasn't required like that 
	
	@Autowired
	public HotelService hotelService;
	
	@Autowired
	public RatingService ratingService;
	
//	private org.slf4j.Logger logger=LoggerFactory.getLogger(UserService.class);
	@Override
	public Micro_User saveUser(Micro_User user) {
		// TODO Auto-generated method stub
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public List<Micro_User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Micro_User getUser(String id) {
		// TODO Auto-generated method stub
		Micro_User user=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with given Id wasn't found on the server"+id));
	
		
		// fetch Ratings of the above user using Ratings Service
		// Rating specific to Id from RatingsService is provided by getRatingByUserId
//using URL :- http://loaclhost:8080/ratings/1{id}
		// different ways to call REST API's, we are using RestTemplate
		// using restTemplate to get the rating Service API's functions
		
		//Infer Generic type Argument as getForObject is Used Return Type Can't be Specifically->Ratings
		
// /**Using Rating Service**/
//ResponseEntity<List<Rating>> response_rtngs= ratingService.getRating(user.getUserId());//.getBody();
//List<Rating> rtngs=response_rtngs.getBody();
	/**Directly Converting into Rating List**/	
//List<Rating> response_rtngs= ratingService.getRating(user.getUserId()).getBody();
 		Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class );
		
		List<Rating> ratings = Arrays.stream(userRatings).toList();
		user.setRatings(ratings);
		/** Using Rest Template**/
		/*
		// adding Hotel w.r.t Id
		//traversing the ratings and adding hotels calling HotelService API w.r.t Id through stream map and then collecting it as list
		List<Rating> ratingList = ratings.stream().map(rating-> {
			// API Call for Hotel Service to get the Hotel
			// from http://localhost: 8081/hotels/Id
			System.out.println(rating.getHotelId());
			
			ResponseEntity<Hotel> hotel_response=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotel_response.getBody();
			
			log.info("response status code:{}", hotel_response.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());*/
		
		List<Rating> ratingList= ratings.stream().map(rating->{
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		
		user.setRatings(ratingList);
		return user;
	}

}
