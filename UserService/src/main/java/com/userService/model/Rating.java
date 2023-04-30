package com.userService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ratings class replica created to map it w.r.t Ratings Service
// Not directly using that, so that no dependency with the other project  

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	 
	private int rating;
	
	private String feedback;
	
	private Hotel hotel;
}
