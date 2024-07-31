package com.example.customerservice.service;

import com.example.customerservice.model.Bank;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankService bankService;

    public Customer saveCustomer(Customer customer) {
        if (customer.getBankDetails() != null && customer.getBankDetails().getId() != null) {
            Bank bank = bankService.getBankById(customer.getBankDetails().getId());
            if (bank != null) {
                // Ensure bank exists and set it in the customer
                customer.setBankDetails(bank);
            } else {
                // Handle case where bank does not exist
                throw new IllegalArgumentException("Bank with ID " + customer.getBankDetails().getId() + " does not exist.");
            }
        } else {
            // Handle case where bank details are null or ID is missing
            throw new IllegalArgumentException("Bank details are missing.");
        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(String id, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setMiddleName(customerDetails.getMiddleName());
            existingCustomer.setLastName(customerDetails.getLastName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setAddress(customerDetails.getAddress());
            existingCustomer.setBankDetails(customerDetails.getBankDetails());
            return customerRepository.save(existingCustomer);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}


