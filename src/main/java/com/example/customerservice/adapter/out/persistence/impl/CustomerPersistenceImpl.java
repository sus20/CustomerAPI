package com.example.customerservice.adapter.out.persistence.impl;

import com.example.customerservice.adapter.out.persistence.CustomerRepository;
import com.example.customerservice.adapter.out.persistence.entity.CustomerEntity;
import com.example.customerservice.adapter.out.persistence.mapper.EntityMapper;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.output.ICustomerOutputPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerPersistenceImpl implements ICustomerOutputPersistencePort {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerRepository.save(getCustomerEntity(customer));
        return getCustomer(customerEntity);
    }

    @Override
    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(String id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return customerEntity.map(this::getCustomer).or(() -> Optional.ofNullable(null));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream().map(this::getCustomer).toList();
    }

    private Customer getCustomer(CustomerEntity customerEntity) {
        return EntityMapper.INSTANCE.toCustomer(customerEntity);
    }

    private CustomerEntity getCustomerEntity(Customer customer) {
        return EntityMapper.INSTANCE.toCustomerEntity(customer);
    }
}
