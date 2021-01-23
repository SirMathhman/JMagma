package com.meti.compile.token;

import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.collect.Sequence;

public interface Attribute {
	default Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	default Sequence<Field> asFieldList() {
		throw new UnsupportedOperationException("Not a list of list.");
	}

	default String asString() {
		throw new UnsupportedOperationException("Not a string.");
	}

	default Token asToken() {
		throw new UnsupportedOperationException("Not a token.");
	}

	default Sequence<Token> asTokenSequence() {
		throw new UnsupportedOperationException("Not a sequence of tokens.");
	}

	enum Type {
		Field_,
		FieldList,
		Node,
		NodeList,
		Type,
		TypeList, Other,
	}

	interface Builder<T> {
		Builder<T> add(T field) throws CollectionException;

		Attribute complete();
	}
}
