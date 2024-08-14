package com.example.customerservice.repository;

import com.example.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <Customer, String>{

    // Method to check if a customer exists by their ID
    boolean existsById(String id);
}
