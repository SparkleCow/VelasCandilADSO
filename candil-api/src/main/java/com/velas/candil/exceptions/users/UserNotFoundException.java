package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(BusinessErrorCode.USER_NOT_FOUND, message);
    }
}
