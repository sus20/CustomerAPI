package com.example.customerservice.exception;

public class BankAccountNotAssociatedException extends RuntimeException {
    public BankAccountNotAssociatedException() {
        super("Bank account not associated with this customer");
    }
}

