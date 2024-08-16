package com.example.customerservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
@TypeAlias("com.example.customerservice.model.Customer")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {
    @Id
    private String            id;
    private String            firstName;
    private String            middleName;
    private String            lastName;
    private String            email;
    private String            phoneNumber;

    @DocumentReference
    private List<Address>     addresses = new ArrayList<>();

    @DocumentReference
    private List<Bank>        bankAccounts = new ArrayList<>();

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}