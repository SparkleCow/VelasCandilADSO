package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class AccountLockedException extends BusinessException {
    public AccountLockedException(String message) {
        super(BusinessErrorCode.ACCOUNT_LOCKED, message);
    }
}
