package com.meti.compile.token;

public record StringAttribute(String value) implements Attribute {
	@Override
	public String asString() {
		return value;
	}
}
