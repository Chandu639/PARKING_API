package com.parking.repository;

import com.parking.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByIsAvailableTrue();
    List<ParkingSpot> findByLocationContainingIgnoreCaseAndIsAvailableTrue(String location);
    List<ParkingSpot> findByOwnerId(Long ownerId);
}
