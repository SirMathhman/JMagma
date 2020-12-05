package com.meti.api.collect;

public class StreamException extends Exception {
	protected StreamException(String message, Throwable cause) {
		super(message, cause);
	}

	public static StreamException StreamException(String message) {
		return StreamException(message, null);
	}

	public static StreamException StreamException(Throwable cause) {
		return StreamException(null, cause);
	}

	public static StreamException StreamException(String message, Throwable cause) {
		return new StreamException(message, cause);
	}
}
