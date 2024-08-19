package com.example.customerservice.adapter.out.repository.impl;

import com.example.customerservice.adapter.out.repository.BankRepository;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.port.output.IBankOutputPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankPersistenceImpl implements IBankOutputPersistencePort {

    private final BankRepository bankRepository;

    @Override
    public List<Bank> saveAll(List<Bank> banks) {
        return bankRepository.saveAll(banks);
    }

    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public boolean existsById(String id) {
        return bankRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Optional<Bank> findById(String id) {
        return bankRepository.findById(id);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }
}
