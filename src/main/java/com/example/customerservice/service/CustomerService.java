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
    private final KafkaProducerService kafkaProducerService;

    public Customer saveCustomer(Customer customer) {
        customer.generateID();
        if (!ObjectUtils.isEmpty(customer.getAddresses())) {
            addressRepository.saveAll(customer.getAddresses());
        }
        if (!ObjectUtils.isEmpty(customer.getBankAccounts())) {
            bankRepository.saveAll(customer.getBankAccounts());
        }

        Customer savedCustomer = customerRepository.save(customer);
        kafkaProducerService.sendMessage("customer-created-topic",savedCustomer);
        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
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

        Customer savedCustomer = customerRepository.save(updatedCustomer);
        kafkaProducerService.sendMessage("customer-update-topic", savedCustomer);

        return savedCustomer;
    }

    public void deleteCustomer(String id) {
        Customer customer = getCustomerById(id);
        customerRepository.deleteById(id);
        kafkaProducerService.sendMessage("customer-delete-topic", customer);
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