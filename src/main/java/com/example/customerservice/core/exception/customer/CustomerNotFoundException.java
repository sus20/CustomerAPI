package com.example.customerservice.core.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Customer with ID " + customerId + " not found.");
    }
}