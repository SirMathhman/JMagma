package com.meti.compile.token;

import java.util.List;

public record FieldAttribute(Field field) implements Attribute {
	@Override
	public Field asField() {
		return field;
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
		throw new UnsupportedOperationException("Not a token.");
	}

	@Override
	public List<Token> asTokenList() {
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
