package com.hotelService.service;

import java.util.List;

import com.hotelService.model.Hotel;

public interface HotelService {

	Hotel create( Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
	
}
