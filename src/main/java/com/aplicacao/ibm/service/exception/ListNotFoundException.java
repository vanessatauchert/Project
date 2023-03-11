package com.aplicacao.ibm.service.exception;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;

public class ListNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ListNotFoundException(String message) {
        super(message);
    }

    @ExcludeFromJacocoGeneratedReport
    public ListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
