package com.example.customerservice.core.port.output;

import com.example.customerservice.core.model.Address;

public interface IAddressEventOutputPort {
    void sendAddressCreatedEvent(Address address);
    void sendAddressUpdatedEvent(Address address);
    void sendAddressDeletedEvent(Address address);
}
