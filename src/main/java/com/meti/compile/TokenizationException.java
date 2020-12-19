package com.meti.compile;

public class TokenizationException extends CompileException {
	private TokenizationException(String message) {
		super(message);
	}

	public static TokenizationException TokenizationException(String message) {
		return new TokenizationException(message);
	}
}
