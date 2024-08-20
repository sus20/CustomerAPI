package com.example.customerservice.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Address>     addresses = new ArrayList<>();
    private List<Bank>        bankAccounts = new ArrayList<>();

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}