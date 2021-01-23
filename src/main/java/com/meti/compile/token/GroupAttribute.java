package com.meti.compile.token;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.Sequence;
import com.meti.api.magma.collect.Sequences;
import com.meti.api.magma.collect.Stream;

import java.util.List;

public enum GroupAttribute implements Attribute {
	Import, Structure, Implementation, Function, Block, Return, Integer, Pair, Parent, Abstraction, Primitive, Content;

	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	private Sequence<Field> asFieldSequence() {
		return JavaLists.fromJava(asFieldList1());
	}

	private List<Field> asFieldList1() {
		throw new UnsupportedOperationException("Not a list1 of list.");
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Not a string.");
	}

	@Override
	public Token asToken() {
		throw new UnsupportedOperationException("Not a token.");
	}

	private Sequence<Token> asTokenSequence() {
		return JavaLists.fromJava(asTokenList1());
	}

	private List<Token> asTokenList1() {
		throw new UnsupportedOperationException("Not a list1 of tokens.");
	}

	@Override
	public Stream<Field> streamFields() {
		return Sequences.stream(asFieldSequence());
	}

	@Override
	public Stream<Token> streamTokens(){
		return Sequences.stream(asTokenSequence());
	}
}
