package com.example.customerservice.service;


import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(ObjectId id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(ObjectId id, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setMiddleName(customerDetails.getMiddleName());
            existingCustomer.setLastName(customerDetails.getLastName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setAddress(customerDetails.getAddress());
            return customerRepository.save(existingCustomer);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteCustomer(ObjectId id) {
        customerRepository.deleteById(id);
    }


}


