package com.example.customerservice.controller;

import com.example.customerservice.model.Bank;
import com.example.customerservice.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank savedBank = bankService.saveBank(bank);
        return new ResponseEntity<>(savedBank, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable String id) {
        Optional<Bank> bank = bankService.getBankById(id);
        return bank.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable String id, @RequestBody Bank updatedBank) {
        Bank bank = bankService.updateBank(id, updatedBank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable String id) {
        bankService.deleteBank(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
