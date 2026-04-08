package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class IllegalOperationException extends BusinessException {
    public IllegalOperationException(String message) {
        super(BusinessErrorCode.ILLEGAL_OPERATION, message);
    }
}
