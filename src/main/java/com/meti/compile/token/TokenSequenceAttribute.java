package com.meti.compile.token;

import com.meti.api.magma.collect.*;

public record TokenSequenceAttribute(Sequence<Token> list) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	private Sequence<Field> asFieldSequence() {
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
		return list;
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
		try {
			return Sequences.stream(list)
					.map(Token::toString)
					.fold((first, second) -> first + "," + second);
		} catch (StreamException e) {
			return "";
		}
	}
}
