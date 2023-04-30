package com.userService.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userService.model.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

	//Declarative Approach
	//Feign Client Implementation provided at runtime	
	@GetMapping("/hotels/{hotelId}") 
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
	// No need to pass Argument in "PathVariable" as arguments in GetMaping and get() method parameter are same
	// only use when these name are not same
	
}
