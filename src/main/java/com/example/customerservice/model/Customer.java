package com.example.customerservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private ObjectId id;
    private String name;
    private Integer age;

}
