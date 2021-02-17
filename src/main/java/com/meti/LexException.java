package com.meti;

public class LexException extends Exception {
	public LexException() {
	}

	public LexException(String message) {
		super(message);
	}

	public LexException(String message, Throwable cause) {
		super(message, cause);
	}

	public LexException(Throwable cause) {
		super(cause);
	}

	public LexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
