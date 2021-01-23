package com.meti.api.magma.collect;

public class ArrayLists {
	public static final int DefaultSize = 10;

	public ArrayLists() {
	}

	@SafeVarargs
	public static <T> List<T> of(T... elements) {
		try {
			return Streams.ofArray(elements).fold(ArrayLists.empty(), List::add);
		} catch (StreamException e) {
			return ArrayLists.empty();
		}
	}

	public static <T> List<T> empty() {
		return new ArrayList<>(new Object[DefaultSize], 0);
	}
}
