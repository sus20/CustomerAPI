package com.example.customerservice.service;

import com.example.customerservice.model.Bank;
import com.example.customerservice.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public Bank saveBank(Bank bank) {
        bank.generateID();
        return bankRepository.save(bank);
    }

    public Optional<Bank> getBankById(String id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank updateBank(String id, Bank updatedBank) {
        Bank existingBank = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Bank with ID " + id + " does not exist.")));

        updatedBank.setId(existingBank.getId());
        return bankRepository.save(updatedBank);
    }

    public void deleteBank(String id) {
        if (!bankRepository.existsById(id)) {
            throw new IllegalArgumentException("Bank with ID " + id + " does not exist.");
        }

        bankRepository.deleteById(id);
    }
}
