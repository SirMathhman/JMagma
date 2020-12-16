package com.meti.api.collect.string;

@Deprecated
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
	public StringBuffer add(char c) {
		return StringBuffer(internalValue + c);
	}

	@Override
	public StringBuffer add(String s) {
		return new SimpleStringBuffer(internalValue + s);
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
