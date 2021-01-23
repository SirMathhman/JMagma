package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.Stream;

import java.util.List;

public record StringAttribute(String value) implements Attribute {
	private Sequence<Field> asFieldSequence() {
		return JavaLists.fromJava(asFieldList1());
	}

	private Sequence<Token> asTokenSequence() {
		return JavaLists.fromJava(asTokenList1());
	}

	@Override
	public Stream<Field> streamFields() {
		return Sequences.stream(asFieldSequence());
	}

	@Override
	public Stream<Token> streamTokens(){
		return Sequences.stream(asTokenSequence());
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
		throw new UnsupportedOperationException("Not a list1 of list.");
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
