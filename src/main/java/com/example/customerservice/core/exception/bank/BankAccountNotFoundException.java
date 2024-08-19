package com.example.customerservice.core.exception.bank;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String bankId) {
        super("Bank details with ID " + bankId + " not found.");
    }
}