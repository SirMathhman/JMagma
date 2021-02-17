package com.meti;

public interface Token {
	default Attribute apply(Attribute.Name name) throws AttributeException {
		throw new AttributeException("No attributes are present.");
	}

	default Token copy(Attribute.Name name, Attribute attribute) throws AttributeException {
		throw new AttributeException("Invalid attribute to copy.");
	}

	enum Type implements Attribute {
		Special, Declaration, Implicit, Content, Integer
	}
}
