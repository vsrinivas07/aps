package com.apceps.exception;

public class InvalidCredentialsException extends java.lang.Exception {

	private static final long serialVersionUID = 1L;

	private String message = null;

	public InvalidCredentialsException() {
		super();
	}

	public InvalidCredentialsException(String message) {
		super(message);
		this.message = message;
	}

	public InvalidCredentialsException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
