package br.com.vr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {

    private UUID id;

    @JsonProperty("numeroCartao")
    private String cardNumber;

    @JsonProperty("senha")
    private String password;

}
