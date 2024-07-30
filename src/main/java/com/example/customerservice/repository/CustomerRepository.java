package com.example.customerservice.repository;

import com.example.customerservice.model.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerRepository extends MongoRepository <Customer, String>{
}
