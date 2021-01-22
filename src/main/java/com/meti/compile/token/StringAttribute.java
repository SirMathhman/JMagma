package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;

import java.util.List;

public record StringAttribute(String value) implements Attribute {
	@Override
	public Sequence<Field> asFieldList() {
		return JavaLists.fromJava(asFieldList1());
	}

	@Override
	public Sequence<Token> asTokenList() {
		return JavaLists.fromJava(asTokenList1());
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
