package com.parking.controller;

import com.parking.model.Booking;
import com.parking.model.ParkingSpot;
import com.parking.model.User;
import com.parking.service.BookingService;
import com.parking.service.ParkingSpotService;
import com.parking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal; // Import Principal to get the user

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<?> create(Principal principal, // Inject Principal
                                     @RequestParam Long spotId,
                                     @RequestParam int durationHours,
                                     @RequestParam String vehicleType,
                                     @RequestParam int numVehicles) {
        try {
            String userEmail = principal.getName(); // Get email from JWT
            User user = userService.findByEmail(userEmail); // Find user by email

            if (user == null) {
                return ResponseEntity.status(401).body("Invalid user associated with the token."); // Handle invalid user
            }
            ParkingSpot spot = parkingSpotService.findById(spotId);
            Booking booking = bookingService.createBooking(user, spot, durationHours, vehicleType, numVehicles);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
}
