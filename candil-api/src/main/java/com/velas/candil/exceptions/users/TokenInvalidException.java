package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class TokenInvalidException extends BusinessException {
    public TokenInvalidException(String message) {
        super(BusinessErrorCode.TOKEN_INVALID);
    }
}
