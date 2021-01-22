package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;

import java.util.List;
import java.util.stream.Collectors;

public record TokenListAttribute(List<Token> list) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	@Override
	public Sequence<Field> asFieldList() {
		return JavaLists.fromJava(asFieldList1());
	}

	private List<Field> asFieldList1() {
		throw new UnsupportedOperationException("Not a list1 of fields.");
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
	public Sequence<Token> asTokenList() {
		return JavaLists.fromJava(asTokenList1());
	}

	private List<Token> asTokenList1() {
		return list;
	}

	@Override
	public String toString() {
		return list.stream()
				.map(Token::toString)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
