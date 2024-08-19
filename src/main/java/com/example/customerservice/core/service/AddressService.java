package com.example.customerservice.core.service;

import com.example.customerservice.core.exception.address.AddressAlreadyExistsException;
import com.example.customerservice.core.exception.address.AddressIdMismatchException;
import com.example.customerservice.core.exception.address.AddressNotBelongToCustomerException;
import com.example.customerservice.core.exception.address.AddressNotFoundException;
import com.example.customerservice.core.exception.customer.CustomerNotFoundException;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.input.IAddressInputPort;
import com.example.customerservice.core.port.output.IAddressEventOutputPort;
import com.example.customerservice.core.port.output.IAddressOutputPersistencePort;
import com.example.customerservice.core.port.output.ICustomerOutputPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AddressService implements IAddressInputPort {

    private final ICustomerOutputPersistencePort customerOutputPersistencePort;
    private final IAddressOutputPersistencePort addressOutputPersistencePort;

    private final IAddressEventOutputPort addressEventOutputPort;

    public Address saveAddress(String customerId, Address address) {
        Customer customer = getCustomer(customerId);
        verifyAddressUniqueness(customer, address);

        address.generateID();
        customer.getAddresses().add(address);

        customerOutputPersistencePort.save(customer);
        addressOutputPersistencePort.saveAll(customer.getAddresses());

        Address savedAddress = getAddress(address.getId());
        addressEventOutputPort.sendAddressCreatedEvent(savedAddress);
        log.info("Address with id: " + address.getId() + " created.");

        return savedAddress;
    }

    public List<Address> getAddressesByCustomerId(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getAddresses();
    }


    public Address updateAddress(String customerId, String addressId, Address updatedAddress) {
        Customer customer = getCustomer(customerId);

        verifyAddressIdConsistency(addressId, updatedAddress);
        verifyAddressBelongsToCustomer(customer, addressId);

        Address savedAddress = addressOutputPersistencePort.save(updatedAddress);
        addressEventOutputPort.sendAddressUpdatedEvent(savedAddress);
        log.info("Address with id: " + savedAddress.getId() + " updated.");

        return savedAddress;
    }

    public void deleteAddress(String customerId, String addressId) {
        Customer customer = getCustomer(customerId);
        verifyAddressBelongsToCustomer(customer, addressId);

        customer.getAddresses().remove(getAddress(addressId));
        customerOutputPersistencePort.save(customer);

        Address address = getAddress(addressId);
        addressEventOutputPort.sendAddressDeletedEvent(address);
        log.info("Address with id: " + address.getId() + " deleted.");

        addressOutputPersistencePort.deleteById(addressId);
    }

    private Customer getCustomer(String customerId) {
        return customerOutputPersistencePort.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Address getAddress(String addressId) {
        return addressOutputPersistencePort.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
    }

    private void verifyAddressUniqueness(Customer customer, Address address) {
        boolean hasDuplicate = customer.getAddresses()
                .stream()
                .anyMatch(existingAddress -> existingAddress.equals(address));
        if (hasDuplicate) {
            throw new AddressAlreadyExistsException();
        }
    }

    private void verifyAddressIdConsistency(String addressPathId, Address updatedAddress) {
        if (!StringUtils.equals(updatedAddress.getId(), addressPathId)) {
            throw new AddressIdMismatchException();
        }
    }

    private void verifyAddressBelongsToCustomer(Customer customer, String addressId) {
        boolean exists = customer.getAddresses()
                .stream()
                .anyMatch(address -> address.getId().equals(addressId));

        if (!exists) {
            throw new AddressNotBelongToCustomerException(addressId);
        }
    }
}