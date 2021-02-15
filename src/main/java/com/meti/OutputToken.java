package com.meti;

public record OutputToken(Output value) implements Token {
	@Override
	public Attribute apply(Attribute.Name name) {
		return new OutputAttribute(value);
	}
}
