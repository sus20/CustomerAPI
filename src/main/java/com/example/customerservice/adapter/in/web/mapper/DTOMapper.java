package com.example.customerservice.adapter.in.web.mapper;

import com.example.customerservice.adapter.in.web.dto.AddressDTO;
import com.example.customerservice.adapter.in.web.dto.BankDTO;
import com.example.customerservice.adapter.in.web.dto.CustomerDTO;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    Address toAddress(AddressDTO addressDTO);
    AddressDTO toAddressDTO(Address address);

    Bank toBank(BankDTO bankDTO);
    BankDTO toBankDTO(Bank bank);

    Customer toCustomer(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
}