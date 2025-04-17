package com.parking.controller;

import com.parking.model.ParkingSpot;
import com.parking.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spots")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService spotService;

    @PostMapping
    public ParkingSpot add(@RequestBody ParkingSpot spot) {
        return spotService.addSpot(spot);
    }

    @GetMapping
    public List<ParkingSpot> getAll() {
        return spotService.getAll();
    }

    @GetMapping("/{id}")
    public ParkingSpot getById(@PathVariable Long id) {
        return spotService.getById(id);
    }
}