package com.example.customerservice.adapter.out.repository;

import com.example.customerservice.core.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <Customer, String>{
}