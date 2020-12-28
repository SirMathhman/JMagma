package com.meti.compile.token;

import com.meti.compile.CompileException;

public class TokenizationException extends CompileException {
	private TokenizationException(String message) {
		super(message);
	}

	public static TokenizationException TokenizationException(String message) {
		return new TokenizationException(message);
	}
}
