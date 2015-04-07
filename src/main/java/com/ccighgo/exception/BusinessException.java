package com.ccighgo.exception;

public class BusinessException extends CcighgoException {

	private static final long serialVersionUID = 6118592685878752124L;

	private ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode, String errorText){
		super(errorText);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
