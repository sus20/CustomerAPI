package com.example.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String      id;
    private String      firstName;
    private String      middleName;
    private String      lastName;
    private String      email;
    private String      phoneNumber;
    private Address     address;
    private Bank        bankDetails;

    public void generateID() {
        this.id = UUID.randomUUID().toString();
    }
}