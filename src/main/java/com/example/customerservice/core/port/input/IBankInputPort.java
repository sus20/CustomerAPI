package com.example.customerservice.core.port.input;

import com.example.customerservice.core.model.Bank;

import java.util.List;

public interface IBankInputPort {
    Bank saveBank(String customerId, Bank bank);
    List<Bank> getBanksByCustomerId(String customerId);
    Bank updateBankDetails(String customerId, String bankDetailsId, Bank updatedBank);
    void deleteBankDetails(String customerId, String bankDetailsId);
}
