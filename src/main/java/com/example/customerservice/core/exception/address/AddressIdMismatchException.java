package com.example.customerservice.core.exception.address;

public class AddressIdMismatchException extends RuntimeException {
    public AddressIdMismatchException() {
        super("The address ID in the request body does not match the ID in the URL path.");
    }
}