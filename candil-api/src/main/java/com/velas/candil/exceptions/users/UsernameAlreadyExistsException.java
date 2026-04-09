package com.velas.candil.exceptions.users;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class UsernameAlreadyExistsException extends BusinessException {
    public UsernameAlreadyExistsException(String message) {
        super(BusinessErrorCode.USER_ALREADY_EXIST,message);
    }
}
