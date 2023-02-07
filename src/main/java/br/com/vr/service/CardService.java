package br.com.vr.service;

import br.com.vr.dto.CardResponseDTO;
import br.com.vr.dto.CreateCardDTO;
import br.com.vr.entities.CardEntity;

import java.math.BigDecimal;
import java.util.Optional;

public interface CardService {
    CardResponseDTO create(CreateCardDTO createCardDTO);

    BigDecimal getBalance(String cardNumber);

    Optional<CardEntity> findByCardNumber(String cardNumber);

    void update(CardEntity cardEntity);
}
