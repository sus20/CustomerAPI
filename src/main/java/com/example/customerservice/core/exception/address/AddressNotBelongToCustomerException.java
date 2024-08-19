package com.example.customerservice.core.exception.address;

public class AddressNotBelongToCustomerException extends RuntimeException {
    public AddressNotBelongToCustomerException(String addressId) {
        super("Address with ID " + addressId + " does not belong to this customer.");
    }
}
