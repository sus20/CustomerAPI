package com.example.customerservice.core.port.output;

import com.example.customerservice.core.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerOutputPersistencePort {
    Customer save(Customer customer);
    boolean existsById(String id);
    void deleteById(String id);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
}
