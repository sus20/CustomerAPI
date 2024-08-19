package com.example.customerservice.core.exception.bank;

public class BankAccountNotBelongToCustomerException extends RuntimeException {
    public BankAccountNotBelongToCustomerException() {
        super("Bank account not associated with this customer");
    }
}