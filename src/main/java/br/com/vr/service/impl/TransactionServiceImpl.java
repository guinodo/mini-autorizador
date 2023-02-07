package br.com.vr.service.impl;

import br.com.vr.domain.TransactionStatus;
import br.com.vr.dto.RequestTransactionDTO;
import br.com.vr.entities.CardEntity;
import br.com.vr.entities.TransactionEntity;
import br.com.vr.exception.TransactionException;
import br.com.vr.repository.TransactionRepository;
import br.com.vr.service.CardService;
import br.com.vr.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository repository;

    @Autowired
    CardService cardService;

    @Override
    public void create(RequestTransactionDTO requestTransaction) {

        synchronized (requestTransaction) {
            log.info("Request create: { number: {}, amount }", requestTransaction.getCardNumber(), requestTransaction.getAmount());

            CardEntity card = cardService.findByCardNumber(requestTransaction.getCardNumber()).orElseThrow(() ->
                    new TransactionException(TransactionStatus.CARTAO_INEXISTENTE));

            validateTransaction(requestTransaction, card);

            var newBalance = card.getBalance().subtract(requestTransaction.getAmount());
            card.setBalance(newBalance);

            cardService.update(card);
        }
    }

    private void saveTransaction(RequestTransactionDTO requestTransactionDTO) {

        var transactionEntity =  TransactionEntity.builder().id(UUID.randomUUID())
                .amount(requestTransactionDTO.getAmount())
                .cardNumber(requestTransactionDTO.getCardNumber())
                .createdAt(LocalDateTime.now()).build();

        repository.save(transactionEntity);
    }

    private void validateTransaction(RequestTransactionDTO request, CardEntity card) {

        if (!card.getPassword().equalsIgnoreCase(request.getPassword())) {
            log.error("ValidateTransaction method : { error: {} }", TransactionStatus.SENHA_INVALIDA );
            throw new TransactionException(TransactionStatus.SENHA_INVALIDA);
        }

        if (card.getBalance().compareTo(request.getAmount()) < 0 || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("ValidateTransaction method : { error: {} }", TransactionStatus.SALDO_INSUFICIENTE );
            throw new TransactionException(TransactionStatus.SALDO_INSUFICIENTE);
        }

    }

}
