package com.meti.api.magma.collect;

public class Sequences {
	public Sequences() {
	}

	public static <T> boolean equals(Sequence<T> self, Sequence<T> other) {
		var selfSize = self.size();
		var otherSize = other.size();
		if (selfSize == otherSize) {
			for (int i = 0; i < selfSize; i++) {
				try {
					var selfElement = self.apply(i);
					var otherElement = other.apply(i);
					if (!selfElement.equals(otherElement)) return false;
				} catch (IndexException e) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static <T> Sequence<T> of(T... elements) {
		return new Impl<>(elements);
	}

	public static <T> Stream<T> stream(Sequence<T> sequence) {
		return new StreamImpl<>(sequence);
	}

	private static class Impl<T> implements Sequence<T> {
		private final int length;
		private final T[] elements;

		@SafeVarargs
		Impl(T... elements) {
			this.elements = elements;
			this.length = elements.length;
		}

		@Override
		public T apply(int index) throws IndexException {
			if (index < 0) {
				var format = "Index of '%d' is negative.";
				var message = format.formatted(index);
				throw new IndexException(message);
			}
			if (index >= length) {
				var format = "Index of '%d' exceeds or is equal to length.";
				var message = format.formatted(index);
				throw new IndexException(message);
			}
			return elements[index];
		}

		@Override
		public int size() {
			return length;
		}
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
