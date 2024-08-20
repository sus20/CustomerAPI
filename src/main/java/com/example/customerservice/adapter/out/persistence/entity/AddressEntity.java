package com.example.customerservice.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "addresses")
public class AddressEntity {
    @Id
    private String id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}