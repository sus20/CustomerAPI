package com.example.customerservice.adapter.out.repository.impl;

import com.example.customerservice.adapter.out.repository.AddressRepository;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.port.output.IAddressOutputPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressPersistenceImpl implements IAddressOutputPersistencePort {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> saveAll(List<Address> addresses) {
        return addressRepository.saveAll(addresses);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean existsById(String id) {
        return addressRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> findById(String id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
