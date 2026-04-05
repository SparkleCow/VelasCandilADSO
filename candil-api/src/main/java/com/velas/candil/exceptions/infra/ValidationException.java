package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class ValidationException extends BusinessException {
    public ValidationException(String message) {
        super(BusinessErrorCode.VALIDATION_ERROR);
    }
}
