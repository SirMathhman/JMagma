package com.meti.attribute;

public class IntegerAttribute implements Attribute {
	private final int value;

	public IntegerAttribute(int value) {
		this.value = value;
	}

	@Override
	public int computeInt() throws AttributeException {
		return value;
	}
}
