package com.parking.controller;

import com.parking.model.Booking;
import com.parking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.createBooking(booking.getUser(), booking.getSpot());
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
}