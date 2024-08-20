package com.example.customerservice.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
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
}