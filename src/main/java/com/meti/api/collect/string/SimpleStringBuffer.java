package com.meti.api.collect.string;

public class SimpleStringBuffer implements StringBuffer {
	private final String internalValue;

	private SimpleStringBuffer(String internalValue) {
		this.internalValue = internalValue;
	}

	public static StringBuffer StringBuffer() {
		return StringBuffer("");
	}

	public static StringBuffer StringBuffer(String internalValue) {
		return new SimpleStringBuffer(internalValue);
	}

	@Override
	public StringBuffer append(char c) {
		return StringBuffer(internalValue + c);
	}

	@Override
	public String toString() {
		return internalValue;
	}

	@Override
	public String asString() {
		return internalValue;
	}
}
