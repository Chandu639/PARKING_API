package com.parking.service;

import com.parking.model.ParkingSpot;
import com.parking.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository spotRepo;

    public ParkingSpot addSpot(ParkingSpot spot) {
        return spotRepo.save(spot);
    }

    public List<ParkingSpot> getAll() {
        return spotRepo.findAll();
    }

    public ParkingSpot getById(Long id) {
        return spotRepo.findById(id).orElse(null);
    }
}