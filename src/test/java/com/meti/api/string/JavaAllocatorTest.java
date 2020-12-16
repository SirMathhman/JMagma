package com.meti.api.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.memory.Allocator.JavaAllocator;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class JavaAllocatorTest {
	@Test
	void allocateChars() {
		assertArrayEquals(new char[1], JavaAllocator.allocateChars(1));
	}

	@Test
	void allocateObjects() {
		assertArrayEquals(new Object[1], JavaAllocator.allocateObjects(1));
	}
}