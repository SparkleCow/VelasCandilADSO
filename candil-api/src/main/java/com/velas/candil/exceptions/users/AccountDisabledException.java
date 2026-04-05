package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class AccountDisabledException extends BusinessException {
    public AccountDisabledException(String message) {
        super(BusinessErrorCode.ACCOUNT_DISABLED);
    }
}
