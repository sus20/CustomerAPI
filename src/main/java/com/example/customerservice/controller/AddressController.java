package com.example.customerservice.controller;

import com.example.customerservice.model.Address;
import com.example.customerservice.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/{customerId}/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@PathVariable String customerId,
                                                 @RequestBody Address address) {
        Address createdAddress = addressService.saveAddress(customerId, address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses(@PathVariable String customerId) {
        List<Address> addresses = addressService.getAddressesByCustomerId(customerId);
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable String customerId,
                                                 @PathVariable String addressId,
                                                 @RequestBody Address updatedAddress) {
        Address address = addressService.updateAddress(customerId, addressId, updatedAddress);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String customerId,
                                              @PathVariable String addressId) {
        addressService.deleteAddress(customerId, addressId);
        return ResponseEntity.noContent().build();
    }
}
