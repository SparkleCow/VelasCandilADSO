package com.velas.candil.exceptions.candles;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class CandleNotFoundException extends BusinessException {
    public CandleNotFoundException(String message) {
        super(BusinessErrorCode.CANDLE_NOT_FOUND, message);
    }
}
