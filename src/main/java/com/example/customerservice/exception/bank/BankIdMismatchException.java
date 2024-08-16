package com.example.customerservice.exception.bank;

public class BankIdMismatchException extends RuntimeException {
    public BankIdMismatchException() {
        super("The bank ID in the request body does not match the ID in the URL path.");
    }
}