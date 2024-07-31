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
        return bankRepository.save(bank);
    }

    public Optional<Bank> getBankById(String id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank updateBank(String id, Bank updatedBank) {
       Optional <Bank> bankDetails  = bankRepository.findById(id);

        if (bankDetails.isEmpty()) {
            throw new IllegalArgumentException("Bank with ID " + id + " does not exist.");
        }
        Bank existingBank = bankDetails.get();
        existingBank.setBankName(updatedBank.getBankName());
        existingBank.setAccountNumber(updatedBank.getAccountNumber());
        existingBank.setAccountType(updatedBank.getAccountType());
        existingBank.setSortCode(updatedBank.getSortCode());
        existingBank.setSwiftCode(updatedBank.getSwiftCode());
        return bankRepository.save(existingBank);
    }

    public void deleteBank(String id) {
        if (!bankRepository.existsById(id)) {
            throw new IllegalArgumentException("Bank with ID " + id + " does not exist.");
        }
        bankRepository.deleteById(id);
    }
}
