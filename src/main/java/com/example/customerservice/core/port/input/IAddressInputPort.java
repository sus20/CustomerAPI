package com.example.customerservice.core.port.input;

import com.example.customerservice.core.model.Address;

import java.util.List;

public interface IAddressInputPort {
    Address saveAddress(String customerId, Address address);
    List<Address> getAddressesByCustomerId(String customerId);
    Address updateAddress(String customerId, String addressId, Address updatedAddress);
    void deleteAddress(String customerId, String addressId);
}
