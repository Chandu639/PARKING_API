package com.parking.repository;

import com.parking.model.User;


//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 User findByEmail(String email);
}