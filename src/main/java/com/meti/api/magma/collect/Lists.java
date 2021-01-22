package com.meti.api.magma.collect;

public class Lists {
	public Lists() {
	}

	public static <T> Stream<T> stream(List<T> list) {
		return new StreamImpl<>(list);
	}

	private static class StreamImpl<T> extends AbstractStream<T> {
		private final List<T> list;
		private int counter;

		public StreamImpl(List<T> list) {
			this.list = list;
			counter = 0;
		}

		@Override
		public T head() throws StreamException {
			if (counter < list.size()) {
				try {
					return list.apply(counter++);
				} catch (IndexException e) {
					throw new StreamException(e);
				}
			} else {
				throw new EndOfStreamException("No more elements left.");
			}
		}
	}
}
