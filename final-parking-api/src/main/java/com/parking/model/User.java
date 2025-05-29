package com.parking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    
    // Don't include password in JSON responses
    @ToString.Exclude
    private String password;
    
    // Add this if you have a bidirectional relationship
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    @ToString.Exclude
    private List<ParkingSpot> parkingSpots;
}