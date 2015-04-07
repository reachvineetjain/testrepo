package com.ccighgo.exception;


public class CcighgoServiceException extends CcighgoException{
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public CcighgoServiceException(ErrorCode errorCode, String errorText){
        super(errorText);
        this.errorCode = errorCode;
    }
    public CcighgoServiceException(ErrorCode errorCode,  String errorText,Throwable cause){
        super(errorText, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
