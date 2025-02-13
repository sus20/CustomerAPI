package com.example.customerservice.core.exception.address;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String addressId) {
        super("Address with ID " + addressId + " not found.");
    }
}