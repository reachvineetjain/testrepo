package com.ccighgo.exception;

public class CcighgoException extends RuntimeException {
    
    private static final long serialVersionUID = -6476443469797807922L;

    public CcighgoException(String errorText) {
        super(errorText);
    }

    public CcighgoException(String errorText, Throwable cause) {
        super(errorText, cause);
    }
}
