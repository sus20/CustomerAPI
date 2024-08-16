package com.example.customerservice.service;

import com.example.customerservice.exception.bank.BankAccountAlreadyExistsException;
import com.example.customerservice.exception.bank.BankAccountNotBelongToCustomerException;
import com.example.customerservice.exception.bank.BankAccountNotFoundException;
import com.example.customerservice.exception.bank.BankIdMismatchException;
import com.example.customerservice.exception.customer.CustomerNotFoundException;
import com.example.customerservice.model.Bank;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.BankRepository;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;

    public Bank saveBank(String customerId, Bank bank) {
        Customer customer = getCustomer(customerId);
        verifyBankAccountUniqueness(customer, bank);

        bank.generateID();
        customer.getBankAccounts().add(bank);

        customerRepository.save(customer);
        bankRepository.saveAll(customer.getBankAccounts());

        return bankRepository.findById(bank.getId()).orElseThrow();
    }

    public List<Bank> getBanksByCustomerId(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getBankAccounts();
    }

    public Bank updateBankDetails(String customerId, String bankDetailsId, Bank updatedBank) {
        Customer customer = getCustomer(customerId);

        verifyBankIdConsistency(bankDetailsId, updatedBank);
        verifyBankAccountBelongsToCustomer(customer, bankDetailsId);

        return bankRepository.save(updatedBank);
    }

    public void deleteBankDetails(String customerId, String bankDetailsId) {
        Customer customer = getCustomer(customerId);
        verifyBankAccountBelongsToCustomer(customer, bankDetailsId);

        customer.getBankAccounts().remove(getBank(bankDetailsId));

        customerRepository.save(customer);
        bankRepository.deleteById(bankDetailsId);
    }

    private void verifyBankAccountUniqueness(Customer customer, Bank bank) {
        boolean hasDuplicate = customer.getBankAccounts()
                                       .stream()
                                       .anyMatch(existingBank -> existingBank.equals(bank));
        if (hasDuplicate) {
            throw new BankAccountAlreadyExistsException(bank.getId());
        }
    }

    private void verifyBankAccountBelongsToCustomer(Customer customer, String bankDetailsId) {
        boolean exists = customer.getBankAccounts()
                .stream()
                .anyMatch(bank -> bank.getId().equals(bankDetailsId));

        if (!exists) {
            throw new BankAccountNotBelongToCustomerException();
        }
    }

    private void verifyBankIdConsistency(String bankDetailsId, Bank updatedBank) {
        if (!StringUtils.pathEquals(updatedBank.getId(), bankDetailsId)) {
            throw new BankIdMismatchException();
        }
    }

    private Customer getCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Bank getBank(String bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankId));
    }
}