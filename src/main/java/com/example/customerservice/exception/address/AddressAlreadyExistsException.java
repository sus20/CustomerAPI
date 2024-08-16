package com.example.customerservice.exception.address;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException() {
        super("Address already exists.");
    }
}