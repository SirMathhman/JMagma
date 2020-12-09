package com.meti.exec.compile.source;

public class LoadException extends Exception {
	private LoadException(Throwable cause) {
		super(cause);
	}

	public LoadException(String message) {
		super(message);
	}

	public static LoadException LoadException(Throwable cause) {
		return new LoadException(cause);
	}
}
