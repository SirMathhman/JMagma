package com.meti.compile.token;

import com.meti.api.magma.collect.Sequence;

public interface Attribute {
	Field asField();

	Sequence<Field> asFieldList();

	String asString();

	Token asToken();

	Sequence<Token> asTokenSequence();

	enum Type {
		Field_,
		FieldList,
		Node,
		NodeList,
		Type,
		TypeList, Other,
	}
}
