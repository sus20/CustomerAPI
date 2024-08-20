package com.example.customerservice.adapter.out.persistence.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
@TypeAlias("com.example.customerservice.model.Customer")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerEntity {
    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @DocumentReference
    private List<AddressEntity>     addresses = new ArrayList<>();

    @DocumentReference
    private List<BankEntity>        bankAccounts = new ArrayList<>();
}