package br.com.vr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTransactionDTO {

    @NotBlank
    @NotBlank
    @JsonProperty("numeroCartao")
    private String cardNumber;

    @NotBlank
    @NotBlank
    @JsonProperty("senhaCartao")
    private String password;

    @NotNull
    @JsonProperty("valor")
    private BigDecimal amount;

}
