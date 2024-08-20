package com.example.customerservice.adapter.in.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
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
}