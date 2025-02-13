package com.example.customerservice.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}