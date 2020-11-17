package com.meti.api.memory;

import java.util.Objects;

public class DefaultAllocator implements Allocator {
	public static final Allocator DefaultAllocator_ = new DefaultAllocator();

	private DefaultAllocator() {
	}

	@Override
	public Object[] allocate(int size) {
		return new Object[size];
	}

	@Override
	public <T> T[] allocateGeneric(int size) {
		return (T[]) new Object[size];
	}
}
