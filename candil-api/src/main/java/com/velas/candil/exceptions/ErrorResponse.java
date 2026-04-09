package com.velas.candil.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String code;
    private String path;
    private List<ErrorDetail> details;
}