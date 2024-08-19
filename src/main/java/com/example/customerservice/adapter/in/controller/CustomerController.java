package com.example.customerservice.adapter.in.controller;

import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.input.ICustomerInputPort;
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
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerInputPort.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerInputPort.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerInputPort.getCustomerById(id);

        return ObjectUtils.isEmpty(customer) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        Customer customer = customerInputPort.updateCustomer(id, customerDetails);
        return ObjectUtils.isEmpty(customer) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerInputPort.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @GetMapping("/check/{id}")
    public boolean isRegistered(@PathVariable String id) {
        return customerInputPort.isCustomerRegistered(id);
    }
}
