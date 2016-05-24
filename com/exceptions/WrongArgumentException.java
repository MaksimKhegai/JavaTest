package com.exceptions;

/**
 * Exception class for when there was an error in choosing a search type
 */
public class WrongArgumentException extends ExceptionWithErrorCode {
	private static final long serialVersionUID = 1L;
	public WrongArgumentException() {
		super();
	}
	public WrongArgumentException(String message) {
		super(message);
	}
}
