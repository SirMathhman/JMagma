package com.meti.api.magma.collect;

public class Lists {
	public Lists() {
	}

	public static <T> Stream<T> stream(Sequence<T> sequence) {
		return new StreamImpl<>(sequence);
	}

	private static class StreamImpl<T> extends AbstractStream<T> {
		private final Sequence<T> sequence;
		private int counter;

		public StreamImpl(Sequence<T> sequence) {
			this.sequence = sequence;
			this.counter = 0;
		}

		@Override
		public T head() throws StreamException {
			if (counter < sequence.size()) {
				try {
					return sequence.apply(counter++);
				} catch (IndexException e) {
					throw new StreamException(e);
				}
			} else {
				throw new EndOfStreamException("No more elements left.");
			}
		}
	}
}
