package com.parking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String vehicleType; // CAR or BIKE
    private int numberOfVehicles;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean isCancelled = false;

    private Double totalPrice;
}
