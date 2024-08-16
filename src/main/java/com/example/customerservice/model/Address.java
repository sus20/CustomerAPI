package com.example.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "addresses")
public class Address {
    @Id
    private String      id;
    private String      street;
    private String      city;
    private String      state;
    private String      postalCode;
    private String      country;

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}