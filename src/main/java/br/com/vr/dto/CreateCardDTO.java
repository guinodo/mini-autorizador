package br.com.vr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCardDTO {

    @NotBlank
    @NotBlank
    @JsonProperty("numeroCartao")
    private String cardNumber;

    @NotBlank
    @NotBlank
    @JsonProperty("senha")
    private String password;

}
