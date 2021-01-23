package com.meti.api.magma.collect;

public class Streams {
	public Streams() {
	}

	public static <T> Stream<T> empty() {
		return new AbstractStream<T>() {
			@Override
			public T head() throws StreamException {
				throw new EndOfStreamException("Stream is empty.");
			}
		};
	}

	public static <T> Stream<T> ofArray(T... elements) {
		return new AbstractStream<>() {
			private int counter = 0;

			@Override
			public T head() throws StreamException {
				if (counter < elements.length) {
					return elements[counter++];
				} else {
					throw new EndOfStreamException("No more elements left." + "");
				}
			}
		};
	}

	public static Stream<Integer> ofIntRange(int from, int to) {
		return new AbstractStream<>() {
			private int counter = from;

			@Override
			public Integer head() throws StreamException {
				if (counter < to) {
					return counter++;
				} else {
					throw new EndOfStreamException("No more integers left.");
				}
			}
		};
	}
}
