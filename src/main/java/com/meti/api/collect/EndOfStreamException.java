package com.meti.api.collect;

public class EndOfStreamException extends StreamException {
	public EndOfStreamException(String message) {
		this(message, null);
	}

	public EndOfStreamException(String message, Throwable cause) {
		super(message, cause);
	}
}
