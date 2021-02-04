package com.meti.api.magma.collect.stream;

public class Streams {
	public Streams() {
	}

	public static Stream<Integer> ofIntRange(int from, int to) {
		return new AbstractStream<>() {
			private int counter = from;


			@Override
			protected Integer head() throws StreamException {
				if (counter < to) return counter++;
				else throw new EndOfStreamException("Reached upper bound.");
			}
		};
	}
}
