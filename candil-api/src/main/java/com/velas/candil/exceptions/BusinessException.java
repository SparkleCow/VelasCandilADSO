package com.velas.candil.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BusinessException extends RuntimeException{

    private final BusinessErrorCode businessErrorCode;
}
