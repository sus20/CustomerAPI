package com.example.customerservice.adapter.out.persistence.mapper;


import com.example.customerservice.adapter.out.persistence.entity.AddressEntity;
import com.example.customerservice.adapter.out.persistence.entity.BankEntity;
import com.example.customerservice.adapter.out.persistence.entity.CustomerEntity;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    Address toAddress(AddressEntity addressEntity);
    AddressEntity toAddressEntity (Address address);

    Bank toBank(BankEntity bankDTO);
    BankEntity toBankEntity (Bank bank);

    Customer toCustomer(CustomerEntity customerEntity);
    CustomerEntity toCustomerEntity (Customer customer);

}
