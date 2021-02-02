package com.meti.api.magma.collect.stream;

import com.meti.api.magma.core.Option;

public class Streams {
	public Streams() {
	}

	public static Stream<Integer> ofIntRange(int from, int to) {
		return new AbstractStream<>() {
			private int counter = from;

			@Override
			public Integer head() throws StreamException {
				if (counter < to) return counter++;
				else throw new EndOfStreamException("Reached upper bound.");
			}
		};
	}

	public static <T> Stream<T> of(T... elements) {
		if (elements.length == 0) return empty();
		if (elements.length == 1) return singleton(elements[0]);
		return new AbstractStream<T>() {
			private int counter = 0;

			@Override
			public T head() throws StreamException {
				if (counter < elements.length) {
					return elements[counter++];
				} else {
					throw new EndOfStreamException("No more elements left.");
				}
			}
		};
	}

	public static <T> Stream<T> empty() {
		return new AbstractStream<>() {
			@Override
			public T head() throws StreamException {
				throw new EndOfStreamException("Stream is empty.");
			}
		};
	}

	public static <T> Stream<T> singleton(T element) {
		return new AbstractStream<>() {
			private boolean wasRetrieved = false;

			@Override
			public T head() throws StreamException {
				if (wasRetrieved) {
					throw new EndOfStreamException("Element was already retrieved.");
				} else {
					wasRetrieved = true;
					return element;
				}
			}
		};
	}

	public static <T> Stream<T> ofOption(Option<T> option) {
		return option.map(Streams::singleton).orElseGet(Streams::empty);
	}
}
