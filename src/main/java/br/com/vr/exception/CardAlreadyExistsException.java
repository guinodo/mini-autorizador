package br.com.vr.exception;

import br.com.vr.dto.CreateCardDTO;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class CardAlreadyExistsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private CreateCardDTO response;

    public CardAlreadyExistsException(CreateCardDTO createCardDTO) {
        this.response = createCardDTO;
    }

}
