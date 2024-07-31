package com.example.customerservice.service;

import com.example.customerservice.model.Bank;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankService bankService;

    public Customer saveCustomer(Customer customer) {
        if (customer.getBankDetails() == null || customer.getBankDetails().getId() == null) {
            throw new IllegalArgumentException("Bank details are missing.");
        }
        Optional<Bank> bank = bankService.getBankById(customer.getBankDetails().getId());
        if(bank.isEmpty()){
            throw new IllegalArgumentException("Bank with ID " + customer.getBankDetails().getId() + " does not exist.");
        }
        customer.setBankDetails(bank.get());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(String id, Customer customerDetails) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
        Customer existingCustomer = customer.get();
        existingCustomer.setFirstName(customerDetails.getFirstName());
        existingCustomer.setMiddleName(customerDetails.getMiddleName());
        existingCustomer.setLastName(customerDetails.getLastName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setBankDetails(customerDetails.getBankDetails());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}


