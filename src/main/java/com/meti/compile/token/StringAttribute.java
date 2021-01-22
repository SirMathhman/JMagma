package com.meti.compile.token;

import com.meti.api.java.collect.JavaList;

import java.util.List;

public record StringAttribute(String value) implements Attribute {
	@Override
	public com.meti.api.magma.collect.List<Field> asFieldList() {
		return new JavaList<>(asFieldList1());
	}

	@Override
	public com.meti.api.magma.collect.List<Token> asTokenList() {
		return new JavaList<>(asTokenList1());
	}

	@Override
	public String toString() {
		var withEscapes = value.replace("\"", "\\\"");
		return "\"%s\"".formatted(withEscapes);
	}

	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	private List<Field> asFieldList1() {
		throw new UnsupportedOperationException("Not a list1 of fields.");
	}

	@Override
	public String asString() {
		return value;
	}

	@Override
	public Token asToken() {
		throw new UnsupportedOperationException("Not a token.");
	}

	private List<Token> asTokenList1() {
		throw new UnsupportedOperationException("Not a list1 of tokens.");
	}
}
