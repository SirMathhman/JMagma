package com.meti;

public record OutputAttribute(Output value) implements Attribute {
	@Override
	public Output asOutput() {
		return value;
	}
}
