package com.example.customerservice.service;

import com.example.customerservice.model.Bank;
import com.example.customerservice.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank getBankById(String id) {
        return bankRepository.findById(id).orElse(null);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank updateBank(String id, Bank updatedBank) {
        // Check if the bank exists
        Bank existingBank = bankRepository.findById(id).orElse(null);

        if (existingBank!= null) {
            // Update the fields as needed
            existingBank.setBankName(updatedBank.getBankName());
            existingBank.setAccountNumber(updatedBank.getAccountNumber());
            existingBank.setAccountType(updatedBank.getAccountType());
            existingBank.setSortCode(updatedBank.getSortCode());
            existingBank.setSwiftCode(updatedBank.getSwiftCode());
            return bankRepository.save(existingBank);
        } else {
            throw new IllegalArgumentException("Bank with ID " + id + " does not exist.");
        }
    }

    public void deleteBank(String id) {
        if (bankRepository.existsById(id)) {
            bankRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bank with ID " + id + " does not exist.");
        }
    }
}
