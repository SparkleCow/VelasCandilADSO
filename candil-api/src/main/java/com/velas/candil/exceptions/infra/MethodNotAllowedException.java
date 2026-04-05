package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class MethodNotAllowedException extends BusinessException {
    public MethodNotAllowedException(String message) {
        super(BusinessErrorCode.METHOD_NOT_ALLOWED);
    }
}
