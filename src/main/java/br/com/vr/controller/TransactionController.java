package br.com.vr.controller;

import br.com.vr.dto.CardResponseDTO;
import br.com.vr.dto.CreateCardDTO;
import br.com.vr.dto.RequestTransactionDTO;
import br.com.vr.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
@Slf4j
@Tag(name = "Transações", description = "API Transações")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CardResponseDTO> requestTransaction(@RequestBody @Valid final RequestTransactionDTO transactionDTO) {

        log.info("Request transaction: { cardNumber: {} }", transactionDTO.getCardNumber() );

        transactionService.create(transactionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
