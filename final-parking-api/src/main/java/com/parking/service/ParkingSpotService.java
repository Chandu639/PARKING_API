package com.parking.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.model.ParkingSpot;
import com.parking.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> findAvailableSpots(LocalDateTime startTime, LocalDateTime endTime, String location) {
        List<ParkingSpot> spots;

        if (location != null && !location.isEmpty()) {
            spots = parkingSpotRepository.findByLocationContainingIgnoreCaseAndIsAvailableTrue(location);
        } else {
            spots = parkingSpotRepository.findByIsAvailableTrue();
        }

        return spots.stream()
            .filter(spot -> {
                if (spot.getAvailableFrom() == null || spot.getAvailableTo() == null) {
                    return true;
                }
                return (startTime.isAfter(LocalDateTime.parse(spot.getAvailableFrom())) || 
                        startTime.equals(LocalDateTime.parse(spot.getAvailableFrom()))) &&
                       (endTime.isBefore(LocalDateTime.parse(spot.getAvailableTo())) || 
                        endTime.equals(LocalDateTime.parse(spot.getAvailableTo())));

            })
            .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        parkingSpotRepository.deleteById(id);
    }

    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }

    public ParkingSpot findById(Long id) {
        Optional<ParkingSpot> optionalSpot = parkingSpotRepository.findById(id);
        return optionalSpot.orElse(null);
    }

    public List<ParkingSpot> findByOwnerId(Long ownerId) {
        return parkingSpotRepository.findByOwnerId(ownerId);
    }
}