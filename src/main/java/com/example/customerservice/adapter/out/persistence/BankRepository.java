package com.example.customerservice.adapter.out.persistence;

import com.example.customerservice.adapter.out.persistence.entity.BankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<BankEntity, String> {
}
