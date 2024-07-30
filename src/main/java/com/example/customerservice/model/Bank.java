package com.example.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "banks")
public class Bank {
    @Id
    private String id;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private String sortCode;
    private String swiftCode;
}
