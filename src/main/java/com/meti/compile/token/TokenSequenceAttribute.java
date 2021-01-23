package com.meti.compile.token;

import com.meti.api.magma.collect.*;

public record TokenSequenceAttribute(Sequence<Token> list) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	@Override
	public Sequence<Field> asFieldList() {
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

	@Override
	public Sequence<Token> asTokenSequence() {
		return list;
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
