package com.userService.service;

import java.util.List;

import com.userService.model.Micro_User;

public interface UserService {

	//CRUD
	
	Micro_User saveUser(Micro_User user);
	
	List<Micro_User> getAllUsers();
	
	Micro_User getUser(String id);
}
