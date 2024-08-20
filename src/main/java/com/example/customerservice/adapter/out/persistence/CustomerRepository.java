package com.example.customerservice.adapter.out.persistence;

import com.example.customerservice.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <CustomerEntity, String>{
}