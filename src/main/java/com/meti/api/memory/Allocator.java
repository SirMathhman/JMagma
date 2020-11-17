package com.meti.api.memory;

public interface Allocator {
	Object[] allocate(int size);

	@Deprecated
	<T> T[] allocateGeneric(int size);
}
