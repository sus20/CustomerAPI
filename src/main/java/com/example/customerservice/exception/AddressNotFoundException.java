package com.example.customerservice.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String addressId){
        super("Address with ID " + addressId + " not found.");
    }
}
