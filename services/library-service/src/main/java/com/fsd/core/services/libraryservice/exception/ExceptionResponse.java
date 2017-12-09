package com.fsd.core.services.libraryservice.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;

    public ExceptionResponse() {
    }
}
