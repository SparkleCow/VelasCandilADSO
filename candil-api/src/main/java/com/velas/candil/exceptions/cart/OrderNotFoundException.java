package com.velas.candil.exceptions.cart;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message) {
        super(BusinessErrorCode.ORDER_NOT_FOUND);
    }
}
