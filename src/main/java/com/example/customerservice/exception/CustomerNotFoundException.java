package com.example.customerservice.exception;

import java.util.NoSuchElementException;

public class CustomerNotFoundException extends RuntimeException  {
    public CustomerNotFoundException(String customerId) {
        super("Customer with ID " + customerId + " not found.");
    }
}
