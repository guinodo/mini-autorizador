package br.com.vr.repository;

import br.com.vr.entities.CardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends MongoRepository<CardEntity, UUID> {
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
