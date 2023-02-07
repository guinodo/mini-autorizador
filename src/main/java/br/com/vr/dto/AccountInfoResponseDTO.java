package br.com.vr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponseDTO {

    @JsonProperty("saldo")
    private BigDecimal balance;
    
}
