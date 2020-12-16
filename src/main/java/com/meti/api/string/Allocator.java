package com.meti.api.string;

public interface Allocator {
	Allocator JavaAllocator = new JavaAllocator();

	char[] allocateChars(int length);

	class JavaAllocator implements Allocator {
		@Override
		public char[] allocateChars(int length) {
			return new char[length];
		}
	}
}
