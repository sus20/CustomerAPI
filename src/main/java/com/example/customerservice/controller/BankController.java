package com.example.customerservice.controller;

import com.example.customerservice.model.Bank;
import com.example.customerservice.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/{customerId}/bank-details")
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<?> createBankDetails(@PathVariable String customerId, @RequestBody Bank bank) {
        Bank createdBank = bankService.saveBank(customerId, bank);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks(@PathVariable String customerId) {
            List<Bank> banks = bankService.getBanksByCustomerId(customerId);
            return ResponseEntity.ok(banks);
    }

    @PutMapping("{bankDetailsId}")
    public ResponseEntity<Bank> updateBankDetails(@PathVariable String customerId,
                                                  @PathVariable String bankDetailsId,
                                                  @RequestBody Bank    updatedBank) {

            Bank updatedBankDetails = bankService.updateBankDetails(customerId, bankDetailsId, updatedBank);
            return new ResponseEntity<>(updatedBankDetails, HttpStatus.OK);
    }


    @DeleteMapping("/{bankDetailsId}")
    public ResponseEntity<Void> deleteBankDetails(@PathVariable String customerId, @PathVariable String bankDetailsId) {
            bankService.deleteBankDetails(customerId, bankDetailsId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
