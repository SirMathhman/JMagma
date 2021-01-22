package com.meti.compile.token;

import com.meti.api.java.collect.JavaList;

import java.util.List;

public record FieldAttribute(Field field) implements Attribute {
	@Override
	public Field asField() {
		return field;
	}

	@Override
	public com.meti.api.magma.collect.List<Field> asFieldList() {
		return new JavaList<>(asFieldList1());
	}

	private List<Field> asFieldList1() {
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
	public com.meti.api.magma.collect.List<Token> asTokenList() {
		return new JavaList<>(asTokenList1());
	}

	private List<Token> asTokenList1() {
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
