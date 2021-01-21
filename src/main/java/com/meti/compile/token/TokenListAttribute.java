package com.meti.compile.token;

import java.util.List;
import java.util.stream.Collectors;

public record TokenListAttribute(List<Token> list) implements Attribute {
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
		throw new UnsupportedOperationException("Not a token.");
	}

	@Override
	public List<Token> asTokenList() {
		return list;
	}

	@Override
	public String toString() {
		return list.stream()
				.map(Token::toString)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
