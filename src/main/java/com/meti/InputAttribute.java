package com.meti;

public record InputAttribute(Input input) implements Attribute {
	@Override
	public Input computeInput() throws AttributeException {
		return input;
	}
}
