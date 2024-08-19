package com.example.customerservice.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    private String id;

    @NotBlank(message = "{NotBlank.firstName}")
    private String firstName;

    @NotBlank(message = "{NotBlank.middleName}")
    private String middleName;

    @NotBlank(message = "{NotBlank.lastName}")
    private String lastName;

    @NotBlank(message = "{NotBlank.email}")
    @Email(message = "{Email.email}")
    private String email;

    @NotBlank(message = "{NotBlank.phoneNumber}")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "{Pattern.phoneNumber}")
    private String phoneNumber;

    @DocumentReference
    private List<Address>     addresses = new ArrayList<>();

    @DocumentReference
    private List<Bank>        bankAccounts = new ArrayList<>();

    public void generateID() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}