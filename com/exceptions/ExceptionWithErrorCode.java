package com.exceptions;

import com.database.Common;

public class ExceptionWithErrorCode extends Exception {
	private static final long serialVersionUID = 1L;
	private Common.errorCode errorCode = Common.errorCode.NOERROR;
	
	public ExceptionWithErrorCode() {
		super();
	}
	
	public ExceptionWithErrorCode(String message) {
		super(message);
	}
	
	public ExceptionWithErrorCode(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ExceptionWithErrorCode(Throwable cause) {
		super(cause);
	}
	
	public ExceptionWithErrorCode(String message, Common.errorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ExceptionWithErrorCode(String message, Exception cause, Common.errorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public Common.errorCode GetErrorCode() {
		return this.errorCode;
	}
}
