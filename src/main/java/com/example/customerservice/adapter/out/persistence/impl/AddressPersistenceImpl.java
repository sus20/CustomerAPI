package com.example.customerservice.adapter.out.persistence.impl;

import com.example.customerservice.adapter.out.persistence.AddressRepository;
import com.example.customerservice.adapter.out.persistence.entity.AddressEntity;
import com.example.customerservice.adapter.out.persistence.mapper.EntityMapper;
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
        List<AddressEntity> addressEntityList = addresses.stream()
                .map(this::getAddrEntity)
                .toList();

        return addressRepository.saveAll(addressEntityList)
                .stream()
                .map(this::getAddress)
                .toList();
    }

    @Override
    public Address save(Address address) {
        AddressEntity savedAddress = addressRepository.save(getAddrEntity(address));
        return getAddress(savedAddress);
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
        Optional<AddressEntity> addressEntity = addressRepository.findById(id);
        return Optional.of(getAddress(addressEntity.get()));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(this::getAddress)
                .toList();
    }

    private Address getAddress(AddressEntity addressEntity) {
        return EntityMapper.INSTANCE.toAddress(addressEntity);
    }

    private AddressEntity getAddrEntity(Address address) {
        return EntityMapper.INSTANCE.toAddressEntity(address);
    }
}
