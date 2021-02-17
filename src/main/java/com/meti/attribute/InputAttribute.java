package com.meti.attribute;

import com.meti.token.Input;

public class InputAttribute implements Attribute {
	private final Input input;

	public InputAttribute(Input input) {
		this.input = input;
	}

	@Override
	public Input computeInput() throws AttributeException {
		return input;
	}
}
