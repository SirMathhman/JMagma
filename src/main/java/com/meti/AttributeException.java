package com.meti;

public class AttributeException extends CompileException {
	public AttributeException() {
	}

	public AttributeException(String message) {
		super(message);
	}

	public AttributeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AttributeException(Throwable cause) {
		super(cause);
	}

	public AttributeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
