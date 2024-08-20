package com.example.customerservice.adapter.out.persistence.impl;

import com.example.customerservice.adapter.out.persistence.BankRepository;
import com.example.customerservice.adapter.out.persistence.entity.BankEntity;
import com.example.customerservice.adapter.out.persistence.mapper.EntityMapper;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.port.output.IBankOutputPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankPersistenceImpl implements IBankOutputPersistencePort {

    private final BankRepository bankRepository;

    @Override
    public List<Bank> saveAll(List<Bank> banks) {
        List<BankEntity> bankEntityList = banks.stream().map(this::getBankEntity).toList();
        return bankRepository.saveAll(bankEntityList).stream().map(this::getBank).toList();
    }

    @Override
    public Bank save(Bank bank) {
        BankEntity savedBank = bankRepository.save(getBankEntity(bank));
        return getBank(savedBank);
    }

    @Override
    public boolean existsById(String id) {
        return bankRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Optional<Bank> findById(String id) {
        Optional<BankEntity> bankEntity = bankRepository.findById(id);
        return Optional.of(getBank(bankEntity.get()));
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll().stream().map(this::getBank).toList();
    }

    private Bank getBank(BankEntity bankEntity) {
        return EntityMapper.INSTANCE.toBank(bankEntity);
    }

    private BankEntity getBankEntity(Bank bank) {
        return EntityMapper.INSTANCE.toBankEntity(bank);
    }
}
