package com.example.customerservice.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    private String id;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private String sortCode;
    private String swiftCode;

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}