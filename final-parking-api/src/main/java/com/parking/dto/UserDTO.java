package com.parking.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    
    // No password field
    
    // Constructor to convert from User entity
    public UserDTO(com.parking.model.User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}