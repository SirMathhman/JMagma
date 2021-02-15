package com.meti;

public interface Token {
	Attribute apply(Attribute.Name name) throws AttributeException;

	enum Group implements Attribute {
		Integer, Return
	}
}
