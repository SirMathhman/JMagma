package com.meti.api.magma.collect;

import com.meti.api.magma.core.F1;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.F2E1;

public abstract class AbstractStream<T> implements Stream<T> {
	@Override
	public Stream<T> filter(F1<T, Boolean> predicate) {
		return new AbstractStream<>() {
			@Override
			public T head() throws StreamException {
				T head;
				do {
					head = AbstractStream.this.head();
				} while (!predicate.apply(head));
				return head;
			}
		};
	}

	@Override
	public <R> R fold(R identity, F2E1<R, T, R, ?> folder) throws StreamException {
		R current = identity;
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
	public <R> Stream<R> map(F1E1<T, R, ?> mapper) throws StreamException {
		return new AbstractStream<>() {
			@Override
			public R head() throws StreamException {
				try {
					return mapper.apply(AbstractStream.this.head());
				} catch (Exception e) {
					throw new StreamException(e);
				}
			}
		};
	}
}
