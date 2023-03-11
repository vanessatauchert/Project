package com.aplicacao.ibm.service.exception;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
    @ExcludeFromJacocoGeneratedReport
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
