package com.meti.api.magma.collect;

public class ArrayLists {
	public static final int DefaultSize = 10;

	public ArrayLists() {
	}

	public static <T> List<T> empty() {
		return new ArrayList<>(new Object[DefaultSize], 0);
	}
}
