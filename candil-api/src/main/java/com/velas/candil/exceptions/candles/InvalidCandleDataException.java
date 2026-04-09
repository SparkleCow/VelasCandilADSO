package com.velas.candil.exceptions.candles;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class InvalidCandleDataException extends BusinessException {
    public InvalidCandleDataException(String message) {
        super(BusinessErrorCode.INVALID_CANDLE_DATA, message);
    }
}
