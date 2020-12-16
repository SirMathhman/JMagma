package com.meti.api.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.string.Allocator.JavaAllocator;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class JavaAllocatorTest {
	@Test
	void allocateChars() {
		assertArrayEquals(new char[1], JavaAllocator.allocateChars(1));
	}
}