package com.meti.compile.token;

import java.util.List;

public record TokenAttribute(Token value) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	@Override
	public List<Field> asFieldList() {
		throw new UnsupportedOperationException("Not a list of fields.");
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Not a string.");
	}

	@Override
	public Token asToken() {
		return value;
	}

	@Override
	public List<Token> asTokenList() {
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
