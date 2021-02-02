package com.meti.api.java.collect;

import com.meti.api.magma.collect.stream.*;

import java.util.List;

public class JavaLists {
	public JavaLists() {
	}

	public static <T> Stream<T> stream(List<T> self) {
		if (self.isEmpty()) return Streams.empty();
		if (self.size() == 1) return Streams.singleton(self.get(0));
		return new StreamImpl<>(self);
	}

	public static <T, L extends List<T>> L add(L self, T element) {
		self.add(element);
		return self;
	}

	private static class StreamImpl<T> extends AbstractStream<T> {
		private final List<T> self;
		private int counter;

		public StreamImpl(List<T> self) {
			this.self = self;
			counter = 0;
		}

		@Override
		public T head() throws StreamException {
			var size = self.size();
			if (counter < size) {
				return self.get(counter++);
			} else {
				var format = "Counter exceeds size of %d, no more elements left.";
				var message = format.formatted(size);
				throw new EndOfStreamException(message);
			}
		}
	}
}
