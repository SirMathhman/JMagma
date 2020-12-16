package com.meti.api.memory;

public interface Allocator {
	Allocator JavaAllocator = new JavaAllocator();

	char[] allocateChars(int length);

	Object[] allocateObjects(int length);

	class JavaAllocator implements Allocator {
		@Override
		public char[] allocateChars(int length) {
			return new char[length];
		}

		@Override
		public Object[] allocateObjects(int length) {
			return new Object[length];
		}
	}
}
