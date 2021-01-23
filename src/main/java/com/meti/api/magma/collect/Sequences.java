package com.meti.api.magma.collect;

public class Sequences {
	public Sequences() {
	}

	public static <T> Sequence<T> of(T... elements) {
		return new Impl<>(elements);
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
}
