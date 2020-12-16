package com.meti.api.string;

public class ArrayStringBuffer implements StringBuffer {
	private final char[] value;
	private final Allocator allocator;

	private ArrayStringBuffer(char[] value, Allocator allocator) {
		this.value = value;
		this.allocator = allocator;
	}

	public static StringBuffer ArrayStringBuffer(Allocator allocator) {
		return ArrayStringBuffer(allocator.allocateChars(0), allocator);
	}

	public static StringBuffer ArrayStringBuffer(char[] value, Allocator allocator) {
		return new ArrayStringBuffer(value, allocator);
	}

	@Override
	public StringBuffer add(char c) {
		var oldLength = value.length;
		var newLength = oldLength + 1;
		var newValue = allocator.allocateChars(newLength);
		for (int i = 0; i < oldLength; i++) {
			newValue[i] = value[i];
		}
		newValue[oldLength] = c;
		return ArrayStringBuffer(newValue, allocator);
	}

	@Override
	public String asString() {
		return new String(value);
	}
}
