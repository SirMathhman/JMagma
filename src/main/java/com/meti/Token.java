package com.meti;

public interface Token {
	Attribute apply(Attribute.Name name) throws AttributeException;

	enum Type implements Attribute {
		Integer, Return
	}
}
