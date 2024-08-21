package com.example.customerservice.core.service;

import com.example.customerservice.core.exception.bank.BankAccountAlreadyExistsException;
import com.example.customerservice.core.exception.bank.BankAccountNotBelongToCustomerException;
import com.example.customerservice.core.exception.bank.BankAccountNotFoundException;
import com.example.customerservice.core.exception.bank.BankIdMismatchException;
import com.example.customerservice.core.exception.customer.CustomerNotFoundException;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.input.IBankInputPort;
import com.example.customerservice.core.port.output.IBankEventOutputPort;
import com.example.customerservice.core.port.output.IBankOutputPersistencePort;
import com.example.customerservice.core.port.output.ICustomerOutputPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BankServiceImpl implements IBankInputPort {

    private final ICustomerOutputPersistencePort customerOutputPersistencePort;
    private final IBankOutputPersistencePort bankOutputPersistencePort;

    private final IBankEventOutputPort bankEventOutputPort;

    public Bank saveBank(String customerId, Bank bank) {
        Customer customer = getCustomer(customerId);
        verifyBankAccountUniqueness(customer, bank);

        bank.generateID();
        customer.getBankAccounts().add(bank);

        customerOutputPersistencePort.save(customer);
        bankOutputPersistencePort.saveAll(customer.getBankAccounts());

        Bank savedBank = getBank(bank.getId());
        bankEventOutputPort.sendBankCreatedEvent(savedBank);
        log.info("Bank with id: " + bank.getId() + " created.");

        return savedBank;
    }

    public List<Bank> getBanksByCustomerId(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getBankAccounts();
    }

    public Bank updateBankDetails(String customerId, String bankDetailsId, Bank updatedBank) {
        Customer customer = getCustomer(customerId);

        verifyBankIdConsistency(bankDetailsId, updatedBank);
        verifyBankAccountBelongsToCustomer(customer, bankDetailsId);

        Bank savedBank = bankOutputPersistencePort.save(updatedBank);
        bankEventOutputPort.sendBankUpdatedEvent(savedBank);
        log.info("Bank with id: " + savedBank.getId() + " updated.");

        return savedBank;
    }

    public void deleteBankDetails(String customerId, String bankDetailsId) {
        Customer customer = getCustomer(customerId);
        verifyBankAccountBelongsToCustomer(customer, bankDetailsId);

        customer.getBankAccounts().remove(getBank(bankDetailsId));
        customerOutputPersistencePort.save(customer);

        Bank bank = getBank(bankDetailsId);
        bankEventOutputPort.sendBankDeletedEvent(bank);
        log.info("Bank with id: " + bank.getId() + " deleted.");

        bankOutputPersistencePort.deleteById(bankDetailsId);
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
        if (!StringUtils.equals(updatedBank.getId(), bankDetailsId)) {
            throw new BankIdMismatchException();
        }
    }

    private Customer getCustomer(String customerId) {
        return customerOutputPersistencePort.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Bank getBank(String bankId) {
        return bankOutputPersistencePort.findById(bankId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankId));
    }
}