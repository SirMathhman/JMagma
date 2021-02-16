package com.meti;

public abstract class AbstractStream<T> implements Stream<T> {
	@Override
	public <R> Stream<R> mapE1(F1E1<T, R, ?> mapper) {
		return new AbstractStream<R>() {
			@Override
			public R head() throws StreamException {
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

	@Override
	public boolean allMatch(F1<T, Boolean> predicate) throws StreamException {
		while (true) {
			try {
				var apply = head();
				if (!predicate.apply(apply)) {
					return false;
				}
			} catch (EndOfStreamException e) {
				break;
			}
		}
		return true;
	}
}
