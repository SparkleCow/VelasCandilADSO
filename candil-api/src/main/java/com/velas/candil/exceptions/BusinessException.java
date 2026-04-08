package com.velas.candil.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BusinessException extends RuntimeException{

    private final BusinessErrorCode businessErrorCode;

    public BusinessException(BusinessErrorCode code, String message) {
        super(message);
        this.businessErrorCode = code;
    }

    public BusinessException(BusinessErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.businessErrorCode = code;
    }
}
