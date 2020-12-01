package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.api.extern.ExceptionalFunction0;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class ArrayStream<T> extends AbstractStream<T> {
	private final T[] array;
	private int counter = 0;

	private ArrayStream(T[] array) {
		this.array = array;
	}

	public static <T> ArrayStream<T> ArrayStream(T[] array) {
		return new ArrayStream<>(array);
	}

	@Override
	protected Option<T> next() {
		if (counter < array.length) {
			return Some(array[counter++]);
		} else {
			return None();
		}
	}
}
