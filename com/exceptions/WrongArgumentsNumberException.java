package com.exceptions;

import com.database.Common;

/**
 * Exception class for when there was an error in choosing a search type
 */
public class WrongArgumentsNumberException extends ExceptionWithErrorCode {
	private static final long serialVersionUID = 1L;

	public WrongArgumentsNumberException() {
		super();
	}
	
	public WrongArgumentsNumberException(String message) {
		super(message);
	}
	
	public WrongArgumentsNumberException(String message, Exception cause) {
		super(message, cause);
	}
	
	public WrongArgumentsNumberException(String message, Exception cause, Common.errorCode errorCode) {
		super(message, cause, errorCode);
	}
}
