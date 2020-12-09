package com.meti.api.collect.stream;

public class EndOfStreamException extends StreamException {
	private EndOfStreamException(String message) {
		this(message, null);
	}

	public EndOfStreamException(String message, Throwable cause) {
		super(message, cause);
	}

	public static EndOfStreamException EndOfStreamException(String message) {
		return new EndOfStreamException(message);
	}
}
