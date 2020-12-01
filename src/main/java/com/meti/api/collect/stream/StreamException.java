package com.meti.api.collect.stream;

public class StreamException extends Exception {
	public StreamException(Throwable cause) {
		super(cause);
	}

	public StreamException(String message) {
		super(message);
	}

	public StreamException(String message, Throwable cause) {
		super(message, cause);
	}
}
