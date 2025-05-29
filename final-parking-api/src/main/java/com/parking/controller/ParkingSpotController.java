package com.parking.controller;

import com.parking.dto.ParkingSpotDTO;
import com.parking.model.ParkingSpot;
import com.parking.model.User;
import com.parking.service.ParkingSpotService;
import com.parking.repository.UserRepository;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api/parking-spots")
@CrossOrigin(origins = "*")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    private final UserRepository userRepository;

    public ParkingSpotController(ParkingSpotService parkingSpotService, UserRepository userRepository) {
        this.parkingSpotService = parkingSpotService;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<ParkingSpot>> getAvailableSpots(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam(required = false) String location) {

        LocalDateTime now = LocalDateTime.now();
        if (startTime == null) startTime = now;
        if (endTime == null) endTime = now.plusHours(1);

        List<ParkingSpot> spots = parkingSpotService.findAvailableSpots(startTime, endTime, location);
        return ResponseEntity.ok(spots);
    }

    @PostMapping
    public ResponseEntity<?> createParkingSpot(
        @RequestBody ParkingSpotDTO dto,
        Principal principal
    ) {
        String email = principal.getName();
        User owner = userRepository.findByEmail(email);
        if (owner == null) {
        	 System.out.println("User not found for email: " + email);
            return ResponseEntity.status(401).body("Invalid user");
        }
        System.out.println("JWT Email: " + principal.getName());

        ParkingSpot spot = new ParkingSpot();
        spot.setOwner(owner);
        spot.setLocation(dto.getLocation());
        spot.setPricePerHour(dto.getPricePerHour());
        spot.setAvailable(dto.isAvailable());
        spot.setAvailableFrom(dto.getAvailableFrom());
        spot.setAvailableTo(dto.getAvailableTo());

        ParkingSpot saved = parkingSpotService.saveParkingSpot(spot);
        return ResponseEntity.ok(saved);
    }

    // ✅ More secure: Only logged-in user can get their own spots
    @GetMapping("/owner")
    public ResponseEntity<List<ParkingSpot>> getMyParkingSpots(Principal principal) {
        String email = principal.getName();
        User owner = userRepository.findByEmail(email);
        if (owner == null) {
            return ResponseEntity.status(401).build();
        }

        List<ParkingSpot> spots = parkingSpotService.findByOwnerId(owner.getId());
        return ResponseEntity.ok(spots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> getParkingSpotById(@PathVariable Long id) {
        ParkingSpot spot = parkingSpotService.findById(id);
        if (spot == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(spot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable Long id, @RequestBody ParkingSpot parkingSpot) {
        ParkingSpot existingSpot = parkingSpotService.findById(id);
        if (existingSpot == null) return ResponseEntity.notFound().build();
        parkingSpot.setId(id);
        ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(parkingSpot);
        return ResponseEntity.ok(updatedSpot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpot(@PathVariable Long id) {
        ParkingSpot existingSpot = parkingSpotService.findById(id);
        if (existingSpot == null) return ResponseEntity.notFound().build();
        parkingSpotService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
