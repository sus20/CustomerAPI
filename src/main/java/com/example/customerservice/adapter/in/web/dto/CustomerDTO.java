package com.example.customerservice.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDTO {
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
    @Pattern(regexp = "^\\+?([0-9][\\s-]?){10,15}$", message = "{Pattern.phoneNumber}")
    private String phoneNumber;

    private List<AddressDTO>     addresses = new ArrayList<>();
    private List<BankDTO>        bankAccounts = new ArrayList<>();
}