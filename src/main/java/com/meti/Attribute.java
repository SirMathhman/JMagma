package com.meti;

public interface Attribute {
	default Input computeInput() throws AttributeException {
		throw new AttributeException("Not input.");
	}

	default Token computeToken() throws AttributeException {
		throw new AttributeException("Not a token.");
	}

	enum Name {
		Group,
		Content,
		Value
	}
}
