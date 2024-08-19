package com.example.customerservice.core.port.output;

import com.example.customerservice.core.model.Bank;

import java.util.List;
import java.util.Optional;

public interface IBankOutputPersistencePort {

    List<Bank> saveAll(List<Bank> banks);

    Bank save(Bank bank);

    boolean existsById(String id);

    void deleteById(String id);

    Optional<Bank> findById(String id);

    List<Bank> findAll();
}
