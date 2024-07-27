package com.example.customerservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private ObjectId id; // ObjectId id
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;

    @DBRef
    private Bank bankDetails;

}
