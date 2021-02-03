package com.meti.api.magma.collect.list;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.stream.AbstractStream;
import com.meti.api.magma.collect.stream.EndOfStreamException;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;

public class ArrayLists {
	private static final int DefaultSize = 10;

	public ArrayLists() {
	}

	@SafeVarargs
	public static <T> List<T> of(T... elements) {
		return new Impl<>(elements, elements.length);
	}

	public static <T> List<T> empty() {
		return new Impl<>(new Object[DefaultSize], 0);
	}

	private static record Impl<T>(Object[] elements, int elementSize) implements List<T> {
		@Override
		public List<T> add(T buffer) {
			try {
				return set(elementSize, buffer);
			} catch (IndexException e) {
				return this;
			}
		}

		@Override
		public T apply(int index) throws IndexException {
			if (index < 0) {
				var format = "Index of %d is negative.";
				var message = format.formatted(index);
				throw new IndexException(message);
			}
			if (index >= elementSize) {
				var format = "Index of %d is greater than equal to length.";
				var message = format.formatted(index);
				throw new IndexException(message);
			}
			return (T) elements[index];
		}

		@Override
		public List<T> set(int index, T element) throws IndexException {
			if (index < 0) {
				var format = "Index of %d is negative.";
				var message = format.formatted(index);
				throw new IndexException(message);
			}

			var newElements = resize(index);
			var newSize = index + 1 > elementSize ? index + 1 : elementSize;
			newElements[index] = element;
			return new Impl<>(newElements, newSize);
		}

		@Override
		public int size() {
			return elementSize;
		}

		@Override
		public Stream<T> stream() {
			return new StreamImpl();
		}

		private Object[] resize(int index) {
			if (index < elementSize) {
				return elements;
			} else {
				var oldCapacity = elements.length;
				var newCapacity = calculateCapacity(oldCapacity == 0 ? 1 : oldCapacity, index);
				var copy = new Object[newCapacity];
				for (int i = 0; i < oldCapacity; i++) {
					copy[i] = elements[i];
				}
				return copy;
			}
		}

		private int calculateCapacity(int oldCapacity, int index) {
			var currentCapacity = oldCapacity;
			while (currentCapacity < index + 1) {
				currentCapacity = currentCapacity * 2;
			}
			return currentCapacity;
		}

		private class StreamImpl extends AbstractStream<T> {
			private int counter = 0;

			@Override
			public T head() throws StreamException {
				if (counter < elementSize) {
					return (T) elements[counter++];
				} else {
					throw new EndOfStreamException("No more elements left.");
				}
			}
		}
	}
}
