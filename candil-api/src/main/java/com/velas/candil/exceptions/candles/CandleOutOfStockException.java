package com.velas.candil.exceptions.candles;

import com.velas.candil.exceptions.BusinessErrorCode;
import com.velas.candil.exceptions.BusinessException;

public class CandleOutOfStockException extends BusinessException {
    public CandleOutOfStockException(String message) {
        super(BusinessErrorCode.CANDLE_OUT_OF_STOCK, message);
    }
}
