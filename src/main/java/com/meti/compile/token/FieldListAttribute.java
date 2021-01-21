package com.meti.compile.token;

import java.util.List;

public record FieldListAttribute(List<Field> fields) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	@Override
	public List<Field> asFieldList() {
		return fields;
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Not a string.");
	}

	@Override
	public Token asToken() {
		throw new UnsupportedOperationException("Not a token.");
	}

	@Override
	public List<Token> asTokenList() {
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
