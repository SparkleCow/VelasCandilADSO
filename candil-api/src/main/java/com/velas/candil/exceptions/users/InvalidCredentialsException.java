package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException(String message) {
        super(BusinessErrorCode.INVALID_CREDENTIALS);
    }
}
