package com.meti;

public interface Attribute {
	Output asOutput();

	default Input computeInput() throws AttributeException {
		throw new AttributeException("Not input.");
	}

	enum Name {
		Output
	}
}
