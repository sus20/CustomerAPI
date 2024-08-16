package com.example.customerservice.exception;

public class BankAccountNotFoundException extends RuntimeException  {
    public BankAccountNotFoundException(String bankId) {
        super("Bank details with ID " + bankId + " not found.");
    }
}
