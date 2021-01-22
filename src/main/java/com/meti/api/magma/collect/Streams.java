package com.meti.api.magma.collect;

public class Streams {
	public Streams() {
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
