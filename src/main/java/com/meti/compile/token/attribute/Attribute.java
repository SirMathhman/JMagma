package com.meti.compile.token.attribute;

import com.meti.compile.token.Field;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

public interface Attribute {
	default boolean computeBoolean() throws AttributeException {
		throw new AttributeException("Not a boolean.");
	}

	default Field computeField() throws AttributeException {
		throw new AttributeException("Not a field");
	}

	default int computeInt() throws AttributeException {
		throw new AttributeException("Not an integer.");
	}

	default Input computeInput() throws AttributeException {
		throw new AttributeException("Not a string.");
	}

	default Token computeToken() throws AttributeException {
		throw new AttributeException("Not a token.");
	}

	enum Name {
		Value,
		Sign,
		Bits,
		Content,
		Identity,
		Type
	}

	enum Type {
		Node,
		Field,
		Type
	}
}
