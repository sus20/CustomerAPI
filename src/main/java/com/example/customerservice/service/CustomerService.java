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
        customer.generateID();
        if (customer.getBankDetails() != null && customer.getBankDetails().getId() != null) {
            Bank bank = bankService.getBankById(customer.getBankDetails().getId())
                                    .orElseThrow(() -> new IllegalArgumentException(("Bank with ID " + customer.getBankDetails().getId() + "does not exist.")));
            customer.setBankDetails(bank);

        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " does not exist."));
    }

    public Customer updateCustomer(String id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " does not exist."));

        updatedCustomer.setId(customer.getId());
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public boolean isCustomerRegistered(String customerId) {
        return customerRepository.existsById(customerId);
    }
}


