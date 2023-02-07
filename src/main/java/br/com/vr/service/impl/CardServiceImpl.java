package br.com.vr.service.impl;

import br.com.vr.dto.CardResponseDTO;
import br.com.vr.dto.CreateCardDTO;
import br.com.vr.entities.CardEntity;
import br.com.vr.exception.CardAlreadyExistsException;
import br.com.vr.exception.CardNotFoundException;
import br.com.vr.repository.CardRepository;
import br.com.vr.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CardRepository cardRepository;

    private static final BigDecimal initialBalance = new BigDecimal(500);

    @Override
    public CardResponseDTO create(CreateCardDTO createCardDTO) {

        log.info("Create card: { number: {} }", createCardDTO.getCardNumber() );

        findByCardNumber(createCardDTO.getCardNumber()).ifPresent(entity -> {
            log.info("Card already exists: {number: {} }", createCardDTO.getCardNumber());
            throw new CardAlreadyExistsException(createCardDTO);
        });

        CardEntity cardEntityResponse = saveCard(createCardDTO);

        log.info("Card created: {id: {}, number: {} }", cardEntityResponse.getId(), createCardDTO.getCardNumber());

        return modelMapper.map(cardEntityResponse, CardResponseDTO.class);
    }

    private CardEntity saveCard(CreateCardDTO createCardDTO) {
        CardEntity cardEntity = modelMapper.map(createCardDTO, CardEntity.class);
        cardEntity.setId(UUID.randomUUID());
        cardEntity.setCreatedAt(LocalDate.now());
        cardEntity.setBalance(initialBalance);

        return cardRepository.save(cardEntity);
    }

    @Override
    public BigDecimal getBalance(String cardNumber) {
        log.info("Request getBalance: { number: {} }", cardNumber );
        CardEntity cardEntity = findByCardNumber(cardNumber).orElseThrow(() -> {
            log.error("Card not found: { number: {} }", cardNumber );
            return new CardNotFoundException();
        });
        return cardEntity.getBalance();
    }

    @Override
    public Optional<CardEntity> findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public void update(CardEntity cardEntity) {
        cardRepository.save(cardEntity);
    }

}
