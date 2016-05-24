package com.exceptions;

import com.database.Common;

/**
 * Exception class for when there was an error in choosing a search type
 */
public class ObjectNotFoundException extends ExceptionWithErrorCode {
	private static final long serialVersionUID = 1L;
	public ObjectNotFoundException() {
		super();
	}
	public ObjectNotFoundException(String message) {
		super(message);
	}
	public ObjectNotFoundException(String message, Common.errorCode errorCode) {
		super(message, errorCode);
	}
}
