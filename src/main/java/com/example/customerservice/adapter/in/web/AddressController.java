package com.example.customerservice.adapter.in.web;

import com.example.customerservice.adapter.in.web.dto.AddressDTO;
import com.example.customerservice.adapter.in.web.mapper.DTOMapper;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.port.input.IAddressInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/{customerId}/address")
public class AddressController {

    private final IAddressInputPort addressInputPort;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@PathVariable String customerId,
                                                 @RequestBody @Valid AddressDTO addressDTO) {
        Address createdAddress = addressInputPort.saveAddress(customerId, DTOMapper.INSTANCE.toAddress(addressDTO));
        return new ResponseEntity<>(DTOMapper.INSTANCE.toAddressDTO(createdAddress), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses(@PathVariable String customerId) {
        List<AddressDTO> addressDTOList = addressInputPort
                                                .getAddressesByCustomerId(customerId)
                                                .stream()
                                                .map(DTOMapper.INSTANCE::toAddressDTO)
                                                .toList();
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable String customerId,
                                                 @PathVariable String addressId,
                                                 @RequestBody @Valid AddressDTO updatedAddressDTO) {
        Address address = addressInputPort.updateAddress(customerId, addressId, DTOMapper.INSTANCE.toAddress(updatedAddressDTO));
        return new ResponseEntity<>(DTOMapper.INSTANCE.toAddressDTO(address), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String customerId,
                                              @PathVariable String addressId) {
        addressInputPort.deleteAddress(customerId, addressId);
        return ResponseEntity.noContent().build();
    }
}
