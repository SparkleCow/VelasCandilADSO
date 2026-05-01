package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class FileCompressionException extends BusinessException {
    //TODO
    public FileCompressionException(String message) {
        super(BusinessErrorCode.USER_ALREADY_EXIST,message);
    }
}
