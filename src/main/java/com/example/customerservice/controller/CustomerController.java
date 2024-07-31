package com.example.customerservice.controller;

import com.example.customerservice.model.Customer;
import com.example.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer != null){
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (IllegalArgumentException e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
