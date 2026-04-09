package com.velas.candil.exceptions.cart;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class CartEmptyException extends BusinessException {
    public CartEmptyException(String message) {
        super(BusinessErrorCode.CART_EMPTY, message);
    }
}
