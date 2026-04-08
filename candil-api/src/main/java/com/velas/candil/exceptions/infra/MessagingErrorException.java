package com.velas.candil.exceptions.infra;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class MessagingErrorException extends BusinessException {

    public MessagingErrorException(String message) {
        super(BusinessErrorCode.EMAIL_SERVICE_UNAVAILABLE, message);
    }
}
