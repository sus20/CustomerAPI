package com.example.customerservice.core.port.output;

import com.example.customerservice.core.model.Customer;

public interface ICustomerEventOutputPort {
    void sendCustomerCreatedEvent(Customer customer);
    void sendCustomerUpdatedEvent(Customer customer);
    void sendCustomerDeletedEvent(Customer customer);
}
