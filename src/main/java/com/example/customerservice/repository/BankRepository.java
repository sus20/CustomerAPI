package com.example.customerservice.repository;

import com.example.customerservice.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {
}
