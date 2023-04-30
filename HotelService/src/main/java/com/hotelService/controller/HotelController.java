package com.hotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelService.model.Hotel;
import com.hotelService.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	public HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel saved_hotel= hotelService.create(hotel);
		return ResponseEntity.ok(saved_hotel);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId)
	{
		Hotel hotel = hotelService.get(hotelId);
		return ResponseEntity.ok(hotel);
		
		// ResponseEntity.status(HttpStatus.OK).body(hotel);
				
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		List<Hotel> hotels = hotelService.getAll();
		return ResponseEntity.ok(hotels);
	}
	
}
