package com.meti.compile.token;

import java.util.List;

public record StringAttribute(String value) implements Attribute {
	@Override
	public String toString() {
		var withEscapes = value.replace("\"", "\\\"");
		return "\"%s\"".formatted(withEscapes);
	}

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
		return value;
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
