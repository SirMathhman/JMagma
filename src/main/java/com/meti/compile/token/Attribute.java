package com.meti.compile.token;

import java.util.List;

public interface Attribute {
	default Field asField() {
		throw new UnsupportedOperationException();
	}

	default List<Field> asFieldList() {
		throw new UnsupportedOperationException();
	}

	default String asString() {
		throw new UnsupportedOperationException();
	}

	default Token asToken() {
		throw new UnsupportedOperationException();
	}

	default List<Token> asTokenList() {
		throw new UnsupportedOperationException();
	}

	enum Type {
		Field_,
		FieldList,
		Node,
		NodeList,
		Type,
		TypeList,
	}
}
