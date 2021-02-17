package com.meti;

public interface Attribute {
	default Field computeField() throws AttributeException {
		throw new AttributeException("Not a field");
	}

	default boolean computeBoolean() throws AttributeException {
		throw new AttributeException("Not a boolean.");
	}

	default int computeInt() throws AttributeException {
		throw new AttributeException("Not an integer.");
	}

	default String computeString() throws AttributeException {
		throw new AttributeException("Not a string.");
	}

	enum Name {
		Value,
		Sign,
		Bits,
		Content,
		Identity, Type
	}

	enum Type {

	}
}
