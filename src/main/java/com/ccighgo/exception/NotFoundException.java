package com.ccighgo.exception;

public class NotFoundException extends CcighgoException {
	private static final long serialVersionUID = -1762283561651258116L;

	public NotFoundException(String errorText) {
		super(errorText);
	}

}
