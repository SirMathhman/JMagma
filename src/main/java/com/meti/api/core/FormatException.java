package com.meti.api.core;

public class FormatException extends Exception {
	private FormatException(String message) {
		super(message);
	}

	public static FormatException FormatException(String message) {
		return new FormatException(message);
	}
}
