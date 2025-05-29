package com.parking.repository;

import com.parking.model.Booking;

import com.parking.model.ParkingSpot;
import com.parking.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByParkingSpot(ParkingSpot parkingSpot);

	List<Booking> findByUser(User user);
}