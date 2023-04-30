package com.userService.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Micro_User {

	@Id
	private String userId;
	
	@Column(length=20)
	private String name;
	private String email;
	private String about;
	
	//Tells JPA to ignore this field and not to store it in the DB.
	@Transient
	private List<Rating> ratings= new ArrayList<>();
	// if we don't initialize it with ArrayList, DB will be store null for this 
}
