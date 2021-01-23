package com.meti.compile.token;

import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;

public record FieldListAttribute(List<Field> fields) implements Attribute {
	@Override
	public Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	@Override
	public Sequence<Field> asFieldList() {
		return fields;
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
		throw new UnsupportedOperationException("Not a list of tokens.");
	}
}
