package com.example.customerservice.core.exception.address;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException() {
        super("Address already exists.");
    }
}