package br.com.vr.controller;

import br.com.vr.dto.BalanceResponseDTO;
import br.com.vr.dto.CardResponseDTO;
import br.com.vr.dto.CreateCardDTO;
import br.com.vr.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
@Slf4j
@Tag(name = "Cartões", description = "API cartões")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CardResponseDTO> create(@RequestBody @Valid final CreateCardDTO createCardDTO) {

        log.info("Request create card: { number: {} }", createCardDTO.getCardNumber() );

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.create(createCardDTO));
    }

    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable(value="cardNumber") String cardNumber) {

        log.info("Request getBalance: { number: {} }", cardNumber );

        var response = BalanceResponseDTO.builder().balance(cardService.getBalance(cardNumber)).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
