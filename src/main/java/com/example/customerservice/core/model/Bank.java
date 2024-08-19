package com.example.customerservice.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "banks")
@TypeAlias("com.example.customerservice.model.Bank")
public class Bank {
    @Id
    private String id;

    @NotBlank(message = "{NotBlank.bankName}")
    private String bankName;

    @NotBlank(message = "{NotBlank.accountNumber}")
    private String accountNumber;

    @NotBlank(message = "{NotBlank.accountType}")
    private String accountType;

    @NotBlank(message = "{NotBlank.sortCode}")
    @Pattern(regexp = "\\d{6}", message = "{Pattern.sortCode}")
    private String sortCode;

    @NotBlank(message = "{NotBlank.swiftCode}")
    @Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$", message = "{Pattern.swiftCode}")
    private String swiftCode;

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}