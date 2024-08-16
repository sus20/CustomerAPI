package com.example.customerservice.exception.bank;

public class BankAccountNotBelongToCustomerException extends RuntimeException {
    public BankAccountNotBelongToCustomerException() {
        super("Bank account not associated with this customer");
    }
}