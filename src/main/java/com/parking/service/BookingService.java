package com.parking.service;

import com.parking.model.*;
import com.parking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    public Booking createBooking(User user, ParkingSpot spot) {
        Booking booking = new Booking(null, user, spot, LocalDateTime.now(), false);
        return bookingRepo.save(booking);
    }

    public List<Booking> getAll() {
        return bookingRepo.findAll();
    }
}