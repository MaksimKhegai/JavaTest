package com.exceptions;

import com.database.Common;

/**
 * Exception class for when there was an error in choosing a search type
 */
public class InvalidSearchTypeException extends ExceptionWithErrorCode {
	private static final long serialVersionUID = 1L;
	public InvalidSearchTypeException() {
		super();
	}
	public InvalidSearchTypeException(String message) {
		super(message);
	}
	public InvalidSearchTypeException(String message, Common.errorCode errorCode) {
		super(message, errorCode);
	}
}
