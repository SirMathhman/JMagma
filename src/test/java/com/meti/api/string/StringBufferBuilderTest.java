package com.meti.api.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.memory.Allocator.JavaAllocator;
import static com.meti.api.string.StringBufferBuilder.StringBufferBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringBufferBuilderTest {

	@Test
	void addInt() {
		assertEquals("test0", StringBufferBuilder("test", JavaAllocator)
				.addInt(0)
				.asString());
	}

	@Test
	void addChar() {
		assertEquals("x", StringBufferBuilder(JavaAllocator)
				.addChar('x')
				.asString());
	}

	@Test
	void addString() {
		assertEquals("test", StringBufferBuilder(JavaAllocator)
				.addString("test")
				.asString());
	}

	@Test
	void asString() {
		var builder = StringBufferBuilder("test", JavaAllocator);
		var builderAsString = builder.asString();
		assertEquals("test", builderAsString);
	}
}