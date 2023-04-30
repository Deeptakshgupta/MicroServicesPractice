package com.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.model.Micro_User;

public interface UserRepositroy extends JpaRepository<Micro_User, String>{

}
