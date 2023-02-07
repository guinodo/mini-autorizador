package br.com.vr.repository;

import br.com.vr.entities.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, UUID> { }
