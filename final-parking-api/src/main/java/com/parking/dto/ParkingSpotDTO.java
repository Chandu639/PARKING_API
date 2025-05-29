package com.parking.dto;

import lombok.Data;

@Data
public class ParkingSpotDTO {
    private String location;
    private Double pricePerHour;
    private boolean isAvailable;
    private String availableFrom;
    private String availableTo;
    
}
