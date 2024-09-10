package com.example.customerservice.adapter.in.web;

import com.example.customerservice.adapter.in.web.dto.CustomerDTO;
import com.example.customerservice.adapter.in.web.mapper.DTOMapper;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.input.ICustomerInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerInputPort customerInputPort;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer( @Valid @RequestBody CustomerDTO customerDTO) {
        Customer savedCustomer = customerInputPort.saveCustomer(DTOMapper.INSTANCE.toCustomer(customerDTO));
        return new ResponseEntity<CustomerDTO>(DTOMapper.INSTANCE.toCustomerDTO(savedCustomer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customersDTOList = customerInputPort.getAllCustomers()
                                                              .stream()
                                                              .map(DTOMapper.INSTANCE::toCustomerDTO)
                                                              .toList();

         return new ResponseEntity<>(customersDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        Customer customer = customerInputPort.getCustomerById(id);

        return (ObjectUtils.isEmpty(customer))
                                             ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                                             : new ResponseEntity<>(DTOMapper.INSTANCE.toCustomerDTO(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerInputPort.updateCustomer(id, DTOMapper.INSTANCE.toCustomer(customerDTO));
        return ObjectUtils.isEmpty(customer)
                                            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                                            : new ResponseEntity<>(DTOMapper.INSTANCE.toCustomerDTO(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerInputPort.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
