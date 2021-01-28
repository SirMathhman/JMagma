package com.meti.api.magma.collect.stream;

import com.meti.api.magma.core.*;

public abstract class AbstractStream<T> implements Stream<T> {
	@Override
	public Stream<T> filter(F1E1<T, Boolean, ?> predicate) {
		return new AbstractStream<>() {
			@Override
			protected T head() throws StreamException {
				try {
					T current;
					do {
						current = AbstractStream.this.head();
					} while (!predicate.apply(current));
					return current;
				} catch (Exception e) {
					throw new StreamException(e);
				}
			}
		};
	}

	@Override
	public <R> R fold(R identity, F2E1<R, T, R, ?> folder) throws StreamException {
		var current = identity;
		while (true) {
			try {
				current = folder.apply(current, head());
			} catch (EndOfStreamException e) {
				break;
			} catch (Exception e) {
				throw new StreamException(e);
			}
		}
		return current;
	}

	@Override
	public Option<T> fold(F2E1<T, T, T, ?> folder) throws StreamException {
		try {
			var current = head();
			while (true) {
				try {
					current = folder.apply(current, head());
				} catch (EndOfStreamException e) {
					break;
				} catch (Exception e) {
					throw new StreamException(e);
				}
			}
			return new Some<>(current);
		} catch (EndOfStreamException e) {
			return new None<>();
		}
	}

	@Override
	public <R> Stream<R> map(F1E1<T, R, ?> mapper) {
		return new AbstractStream<>() {
			@Override
			protected R head() throws StreamException {
				try {
					return mapper.apply(AbstractStream.this.head());
				} catch (EndOfStreamException e) {
					throw e;
				} catch (Exception e) {
					throw new StreamException(e);
				}
			}
		};
	}

	protected abstract T head() throws StreamException;
}
