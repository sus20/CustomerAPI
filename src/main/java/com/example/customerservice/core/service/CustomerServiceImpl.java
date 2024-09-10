package com.example.customerservice.core.service;

import com.example.customerservice.core.exception.customer.CustomerNotFoundException;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.input.ICustomerInputPort;
import com.example.customerservice.core.port.output.IAddressOutputPersistencePort;
import com.example.customerservice.core.port.output.IBankOutputPersistencePort;
import com.example.customerservice.core.port.output.ICustomerEventOutputPort;
import com.example.customerservice.core.port.output.ICustomerOutputPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerInputPort {

    private final ICustomerOutputPersistencePort customerOutputPersistencePort;
    private final IAddressOutputPersistencePort addressOutputPersistencePort;
    private final IBankOutputPersistencePort bankOutputPersistencePort;

    private final ICustomerEventOutputPort customerEventOutputPort;

    public Customer saveCustomer(Customer customer) {
        customer.generateID();
        if (!ObjectUtils.isEmpty(customer.getAddresses())) {
            addressOutputPersistencePort.saveAll(customer.getAddresses());
        }
        if (!ObjectUtils.isEmpty(customer.getBankAccounts())) {
            bankOutputPersistencePort.saveAll(customer.getBankAccounts());
        }

        Customer savedCustomer = customerOutputPersistencePort.save(customer);
        customerEventOutputPort.sendCustomerCreatedEvent(savedCustomer);
        log.info("Customer with id: " + savedCustomer.getId() + " created.");

        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        return customerOutputPersistencePort.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerOutputPersistencePort.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer updateCustomer(String id, Customer updatedCustomer) {
        verifyCustomerIdConsistency(id, updatedCustomer);

        Customer existingCustomer = getCustomerById(id);

        updatedCustomer.setAddresses(existingCustomer.getAddresses());
        updatedCustomer.setBankAccounts(existingCustomer.getBankAccounts());

        Customer savedCustomer = customerOutputPersistencePort.save(updatedCustomer);
        customerEventOutputPort.sendCustomerUpdatedEvent(savedCustomer);
        log.info("Customer with id: " + savedCustomer.getId() + " updated.");

        return savedCustomer;
    }

    public void deleteCustomer(String id) {
        Customer customer = getCustomerById(id);
        customerOutputPersistencePort.deleteById(id);

        customerEventOutputPort.sendCustomerDeletedEvent(customer);
        log.info("Customer with id: " + customer.getId() + " deleted.");
    }

    private void verifyCustomerIdConsistency(String customerId, Customer customer) {
        if (!customer.getId().equals(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
    }
}