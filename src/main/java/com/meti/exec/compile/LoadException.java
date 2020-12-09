package com.meti.exec.compile;

public class LoadException extends Exception {
	public LoadException(Throwable cause) {
		super(cause);
	}

	public LoadException(String message) {
		super(message);
	}
}
