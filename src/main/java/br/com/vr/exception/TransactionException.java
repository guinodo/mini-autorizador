package br.com.vr.exception;

import br.com.vr.domain.TransactionStatus;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class TransactionException extends RuntimeException implements Serializable {

    private TransactionStatus statusError;

    public TransactionException(TransactionStatus statusError) {
        this.statusError = statusError;
    }

}
