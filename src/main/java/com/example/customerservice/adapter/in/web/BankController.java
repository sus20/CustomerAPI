package com.example.customerservice.adapter.in.web;

import com.example.customerservice.adapter.in.web.dto.BankDTO;
import com.example.customerservice.adapter.in.web.mapper.DTOMapper;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.port.input.IBankInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/{customerId}/bank-details")
public class BankController {

    private final IBankInputPort bankInputPort;

    @PostMapping
    public ResponseEntity<BankDTO> createBankDetails(@PathVariable String customerId, @RequestBody @Valid BankDTO bankDTO) {
        Bank createdBank = bankInputPort.saveBank(customerId, DTOMapper.INSTANCE.toBank(bankDTO));
        return new ResponseEntity<>(DTOMapper.INSTANCE.toBankDTO(createdBank), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks(@PathVariable String customerId) {
            List<BankDTO> bankDTOList = bankInputPort.getBanksByCustomerId(customerId)
                                                  .stream()
                                                  .map(DTOMapper.INSTANCE::toBankDTO)
                                                  .toList();
            return new ResponseEntity<>(bankDTOList,HttpStatus.OK);
    }

    @PutMapping("{bankDetailsId}")
    public ResponseEntity<BankDTO> updateBankDetails(@PathVariable String customerId,
                                                  @PathVariable String bankDetailsId,
                                                  @RequestBody BankDTO  updatedBankDTO) {

            Bank updatedBankDetails = bankInputPort.updateBankDetails(customerId, bankDetailsId, DTOMapper.INSTANCE.toBank(updatedBankDTO));
            return new ResponseEntity<>(DTOMapper.INSTANCE.toBankDTO(updatedBankDetails), HttpStatus.OK);
    }


    @DeleteMapping("/{bankDetailsId}")
    public ResponseEntity<Void> deleteBankDetails(@PathVariable String customerId, @PathVariable String bankDetailsId) {
            bankInputPort.deleteBankDetails(customerId, bankDetailsId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
