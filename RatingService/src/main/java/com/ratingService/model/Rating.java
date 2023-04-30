package com.ratingService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 In this video instead of MongoDb 
 
 there is Difference for using MongoDb as  it deals with NOSQL 
 ->> @Document(TableName) instead of Entity
 	 @MongoRepository instead if JpaRepository
 	 
 	 @Id for MongoDb -> Id's are auto generated in MongoDb
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {

	@Id
	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	 
	private int rating;
	
	private String feedback;
	
//	private Hotel hotel;
	
	// can't import Hotel form The other project it will create dependency over the other project  
	// which we are trying to avoid.
	// Hence have to create a replica of Hotel entity class in Rating's model packah 
}

