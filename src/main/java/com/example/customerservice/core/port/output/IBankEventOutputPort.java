package com.example.customerservice.core.port.output;

import com.example.customerservice.core.model.Bank;

public interface IBankEventOutputPort {
    void sendBankCreatedEvent(Bank bank);
    void sendBankUpdatedEvent(Bank bank);
    void sendBankDeletedEvent(Bank bank);
}
