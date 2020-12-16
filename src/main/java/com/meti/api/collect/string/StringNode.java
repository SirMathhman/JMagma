package com.meti.api.collect.string;

import com.meti.api.core.Stringable;

public class StringNode implements JSONNode {
	private final String value;

	private StringNode(String value) {
		this.value = value;
	}

	public static StringNode StringNode(String value) {
		return new StringNode(value);
	}

	@Override
	public String asString() {
		return value;
	}
}
