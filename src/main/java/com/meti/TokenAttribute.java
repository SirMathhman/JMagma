package com.meti;

public record TokenAttribute(Token value) implements Attribute {
	@Override
	public Token computeToken() throws AttributeException {
		return value;
	}
}
