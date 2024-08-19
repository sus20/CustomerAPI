package com.example.customerservice.adapter.out.repository;

import com.example.customerservice.core.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {
}
