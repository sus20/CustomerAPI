package com.example.customerservice.controller;

import com.example.customerservice.model.Bank;
import com.example.customerservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank savedBank = bankService.saveBank(bank);
        return new ResponseEntity<>(savedBank, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable String id) {
        Bank bank = bankService.getBankById(id);
        if (bank != null) {
            return new ResponseEntity<>(bank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable String id, @RequestBody Bank updatedBank) {
        try {
            Bank bank = bankService.updateBank(id, updatedBank);
            return new ResponseEntity<>(bank, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable String id) {
        try {
            bankService.deleteBank(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
