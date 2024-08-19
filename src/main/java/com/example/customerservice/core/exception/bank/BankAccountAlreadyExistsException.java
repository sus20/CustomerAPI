package com.example.customerservice.core.exception.bank;

public class BankAccountAlreadyExistsException extends RuntimeException {
    public BankAccountAlreadyExistsException(String bankId) {
        super("A bank account with this account number " + bankId + "already exists.");
    }
}