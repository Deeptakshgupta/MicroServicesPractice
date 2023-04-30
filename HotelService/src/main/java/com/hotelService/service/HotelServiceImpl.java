package com.hotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelService.exceptionHandler.ResourceNotFoundException;
import com.hotelService.model.Hotel;
import com.hotelService.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	public HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		
		hotel.setId(UUID.randomUUID().toString());
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given Id not Found !!")); 
	}

}
