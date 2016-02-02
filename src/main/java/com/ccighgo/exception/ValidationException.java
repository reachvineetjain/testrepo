package com.ccighgo.exception;

public class ValidationException extends CcighgoException {

	private static final long serialVersionUID = 6118592685878752124L;

	private ErrorCode errorCode;

	public ValidationException(ErrorCode errorCode, String errorText){
		super(errorText);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
