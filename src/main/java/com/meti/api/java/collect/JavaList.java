package com.meti.api.java.collect;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.io.Path;

import java.util.List;

public record JavaList<T>(List<T> value) implements com.meti.api.magma.collect.List<T> {
	@Override
	public T apply(int index) throws IndexException {
		if (index < 0) {
			var format = "Index of '%d' is negative.";
			var message = format.formatted(index);
			throw new IndexException(message);
		} else if (index >= value.size()) {
			var format = "Index of '%d' is greater than or equal to size of '%d'.";
			var message = format.formatted(index, value.size());
			throw new IndexException(message);
		} else {
			return value.get(index);
		}
	}

	@Override
	public int size() {
		return value.size();
	}
}
