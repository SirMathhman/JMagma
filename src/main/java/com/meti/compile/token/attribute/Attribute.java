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

	default Input computeInput() throws AttributeException {
		throw new AttributeException("Not a string.");
	}

	default int computeInt() throws AttributeException {
		throw new AttributeException("Not an integer.");
	}

	default Token computeToken() throws AttributeException {
		throw new AttributeException("Not a token.");
	}

	enum Name {
		Destination,
		Source,
		Type,
		Value,
		Sign,
		Bits,
		Content,
		Identity
	}

	enum Type {
		Node,
		Field,
		Type
	}
}
