package com.parking.service;



import com.parking.model.Booking;



import com.parking.model.ParkingSpot;

import com.parking.model.User;

import com.parking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import java.time.LocalDateTime;

import java.util.List;



@Service

public class BookingService {



@Autowired

private BookingRepository bookingRepo;



public Booking createBooking(User user, ParkingSpot spot, int durationHours, String vehicleType, int numVehicles) {

if (user == null || spot == null || durationHours <= 0 || numVehicles <= 0) {

throw new IllegalArgumentException("Invalid booking parameters");

}



if (spot.getPricePerHour() == null) {

throw new IllegalArgumentException("Parking spot price is not set.");

}



Booking booking = new Booking();

booking.setUser(user);

booking.setParkingSpot(spot);

booking.setStartTime(LocalDateTime.now());

booking.setEndTime(LocalDateTime.now().plusHours(durationHours));

booking.setCancelled(false);

booking.setVehicleType(vehicleType);

booking.setNumberOfVehicles(numVehicles);



double totalPrice = spot.getPricePerHour() * durationHours * numVehicles;

booking.setTotalPrice(totalPrice);



return bookingRepo.save(booking);

}



public List<Booking> getAll() {

return bookingRepo.findAll();

}



public Booking cancelBooking(Long bookingId) {

Booking booking = bookingRepo.findById(bookingId)

.orElseThrow(() -> new RuntimeException("Booking not found"));

booking.setCancelled(true);

return bookingRepo.save(booking);

}



public List<Booking> getBookingsByUser(User user) {

return bookingRepo.findByUser(user);

}

}