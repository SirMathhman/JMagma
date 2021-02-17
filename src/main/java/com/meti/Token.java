package com.meti;

public interface Token {
	default Attribute apply(Attribute.Name name) throws AttributeException {
		throw new AttributeException("No attributes are present.");
	}

	enum Type implements Attribute {
		Special, Declaration, Implicit, Integer
	}
}
