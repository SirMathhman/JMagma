package com.meti.api.string;

import com.meti.api.memory.Allocator;

import static com.meti.api.string.ArrayStringBuffer.ArrayStringBuffer;

public class StringBufferBuilder implements StringBuilder {
	private final StringBuffer buffer;
	private final Allocator allocator;

	private StringBufferBuilder(StringBuffer buffer, Allocator allocator) {
		this.buffer = buffer;
		this.allocator = allocator;
	}

	public static StringBuilder StringBufferBuilder(Allocator allocator) {
		return StringBufferBuilder(ArrayStringBuffer(allocator), allocator);
	}

	public static StringBuilder StringBufferBuilder(String initial, Allocator allocator) {
		return StringBufferBuilder(ArrayStringBuffer(initial.toCharArray(), allocator), allocator);
	}

	public static StringBuilder StringBufferBuilder(StringBuffer buffer, Allocator allocator) {
		return new StringBufferBuilder(buffer, allocator);
	}



	@Override
	public StringBuilder addInt(int value) {
		var valueAsString = Strings.fromInt(value, allocator);
		var inBuffer = buffer.addString(valueAsString);
		return StringBufferBuilder(inBuffer, allocator);
	}

	@Override
	public StringBuilder addChar(char c) {
		return StringBufferBuilder(buffer.addChar(c), allocator);
	}

	@Override
	public StringBuilder addString(String s) {
		return StringBufferBuilder(buffer.addString(s), allocator);
	}

	@Override
	public String asString() {
		return buffer.asString();
	}
}
