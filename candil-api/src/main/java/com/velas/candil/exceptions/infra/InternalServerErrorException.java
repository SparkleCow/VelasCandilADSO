package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException(String message) {
        super(BusinessErrorCode.INTERNAL_SERVER_ERROR, message);
    }
}
