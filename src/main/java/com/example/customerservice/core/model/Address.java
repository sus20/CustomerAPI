package com.example.customerservice.core.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String id;

    @NotNull(message = "{NotNull.street}")
    @NotEmpty(message = "{NotNull.street}")
    private String street;

    @NotNull(message = "{NotNull.city}")
    @NotEmpty(message = "{NotNull.city}")
    private String city;

    @NotNull(message = "{NotNull.state}")
    @NotEmpty(message = "{NotNull.state}")
    private String state;

    @NotNull(message = "{NotNull.postalCode}")
    @NotEmpty(message = "{NotNull.postalCode}")
    private String postalCode;

    @NotNull(message = "{NotNull.country}")
    @NotEmpty(message = "{NotNull.country}")
    private String country;

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}