package com.example.customerservice.core.port.input;

import com.example.customerservice.core.model.Customer;

import java.util.List;

public interface ICustomerInputPort {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    Customer updateCustomer(String id, Customer updatedCustomer);
    void deleteCustomer(String id);
}
