package com.example.customerservice.adapter.out.persistence;

import com.example.customerservice.adapter.out.persistence.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<AddressEntity, String> {
}