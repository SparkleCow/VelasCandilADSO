package com.velas.candil.exceptions.roles;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class RoleNotFoundException extends BusinessException {
    public RoleNotFoundException(String message) {
        super(BusinessErrorCode.ROLE_NOT_FOUND, message);
    }
}
