package com.parking.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

    // Getters and setters
}
