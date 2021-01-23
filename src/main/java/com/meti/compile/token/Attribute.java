package com.meti.compile.token;

import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.collect.Streams;

public interface Attribute {
	default Field asField() {
		throw new UnsupportedOperationException("Not a field.");
	}

	default String asString() {
		throw new UnsupportedOperationException("Not a string.");
	}

	default Token asToken() {
		throw new UnsupportedOperationException("Not a token.");
	}

	default Stream<Field> streamFields() {
		return Streams.empty();
	}

	default Stream<Token> streamTokens() {
		return Streams.empty();
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
