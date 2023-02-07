package br.com.vr.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document("transaction")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {

    @Indexed(unique = true)
    private UUID id;

    @Indexed(unique = true)
    private String cardNumber;

    private BigDecimal balance;

    private String password;

    @Indexed
    private LocalDate createdAt;

}
