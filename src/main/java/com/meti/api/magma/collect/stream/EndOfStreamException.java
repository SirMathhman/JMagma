package com.meti.api.magma.collect.stream;

public class EndOfStreamException extends StreamException {
	public EndOfStreamException() {
	}

	public EndOfStreamException(String message) {
		super(message);
	}

	public EndOfStreamException(String message, Throwable cause) {
		super(message, cause);
	}

	public EndOfStreamException(Throwable cause) {
		super(cause);
	}

	public EndOfStreamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
