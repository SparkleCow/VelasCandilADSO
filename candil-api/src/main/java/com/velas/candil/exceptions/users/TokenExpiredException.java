package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException(String message) {
        super(BusinessErrorCode.TOKEN_EXPIRED, message);
    }
}
