package com.meti;

public class ArrayList<T> implements List<T> {
	public static <T> List<T> empty() {
		return new ArrayList<>();
	}

	@Override
	public List<T> add(T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Stream<T> stream() {
		throw new UnsupportedOperationException();
	}
}
