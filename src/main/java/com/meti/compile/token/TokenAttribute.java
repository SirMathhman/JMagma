package com.meti.compile.token;

public record TokenAttribute(Token value) implements Attribute {
	@Override
	public Token asToken() {
		return value;
	}
}
