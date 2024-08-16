package com.example.customerservice.service;

import com.example.customerservice.exception.customer.CustomerNotFoundException;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.AddressRepository;
import com.example.customerservice.repository.BankRepository;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;
    private final AddressRepository addressRepository;

    public Customer saveCustomer(Customer customer) {
        customer.generateID();
        if (!ObjectUtils.isEmpty(customer.getAddresses())) {
            addressRepository.saveAll(customer.getAddresses());
        }
        if (!ObjectUtils.isEmpty(customer.getBankAccounts())) {
            bankRepository.saveAll(customer.getBankAccounts());
        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer updateCustomer(String id, Customer updatedCustomer) {
        verifyCustomerIdConsistency(id, updatedCustomer);

        Customer existingCustomer = getCustomerById(id);

        updatedCustomer.setAddresses(existingCustomer.getAddresses());
        updatedCustomer.setBankAccounts(existingCustomer.getBankAccounts());

        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public boolean isCustomerRegistered(String id) {
        return customerRepository.existsById(id);
    }

    private void verifyCustomerIdConsistency(String customerId, Customer customer) {
        if (!customer.getId().equals(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
    }
}