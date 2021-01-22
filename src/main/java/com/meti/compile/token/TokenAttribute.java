package com.meti.compile.token;

import com.meti.api.java.collect.JavaList;

import java.util.List;

public record TokenAttribute(Token value) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
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
		return value;
	}

	@Override
	public com.meti.api.magma.collect.List<Token> asTokenList() {
		return new JavaList<>(asTokenList1());
	}

	private List<Token> asTokenList1() {
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
