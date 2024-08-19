package com.example.customerservice.core.port.output;


import com.example.customerservice.core.model.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressOutputPersistencePort {
    List<Address> saveAll(List<Address> addresses);
    Address save(Address address);
    boolean existsById(String id);
    void deleteById(String id);
    Optional<Address> findById(String id);
    List<Address> findAll();
}
