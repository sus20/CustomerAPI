package com.example.customerservice.service;

import com.example.customerservice.exception.address.AddressAlreadyExistsException;
import com.example.customerservice.exception.address.AddressIdMismatchException;
import com.example.customerservice.exception.address.AddressNotBelongToCustomerException;
import com.example.customerservice.exception.address.AddressNotFoundException;
import com.example.customerservice.exception.customer.CustomerNotFoundException;
import com.example.customerservice.model.Address;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.AddressRepository;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;


    public Address saveAddress(String customerId, Address address) {
        Customer customer = getCustomer(customerId);
        verifyAddressUniqueness(customer, address);

        address.generateID();
        customer.getAddresses().add(address);

        customerRepository.save(customer);
        addressRepository.saveAll(customer.getAddresses());

        return addressRepository.findById(address.getId()).orElseThrow();
    }

    public List<Address> getAddressesByCustomerId(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getAddresses();
    }


    public Address updateAddress(String customerId, String addressId, Address updatedAddress) {
        Customer customer = getCustomer(customerId);
        verifyAddressIdConsistency(addressId, updatedAddress);
        verifyAddressBelongsToCustomer(customer, addressId);

        return addressRepository.save(updatedAddress);
    }

    public void deleteAddress(String customerId, String addressId) {
        Customer customer = getCustomer(customerId);
        verifyAddressBelongsToCustomer(customer, addressId);

        customer.getAddresses().remove(getAddress(addressId));

        customerRepository.save(customer);
        addressRepository.deleteById(addressId);
    }

    private Customer getCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Address getAddress(String addressId) {
        return addressRepository.findById(addressId)
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
        if (!StringUtils.pathEquals(updatedAddress.getId(), addressPathId)) {
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
