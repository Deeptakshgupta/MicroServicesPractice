package com.hotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelService.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
