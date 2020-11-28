package com.meti;

import static com.meti.None.None;
import static com.meti.Some.Some;

public class ListStream<T> {
	private final List<T> list;

	public ListStream(List<T> list) {
		this.list = list;
	}


	public boolean anyMatch(Function1<T, Boolean> predicate) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (get(size)
					.filter(predicate)
					.isPresent()) {
				return true;
			}
		}
		return false;
	}

	private Option<T> get(int index) {
		try {
			return Some(list.get(index));
		} catch (IndexException e) {
			return None();
		}
	}

	public ListStream<T> filter(Function1<T, Boolean> predicate) {
		List<T> copy = list.asEmpty();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			copy = get(i).filter(predicate).mapExceptionally(copy::add).orElse(copy);
		}
		return new ListStream<>(copy);
	}

	public <R> ListStream<R> map(ExceptionalFunction1<T, R, ?> mapper) throws StreamException {
		return supplyExceptionally(() -> {
			List<R> copy = list.asEmpty();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				copy = get(i).mapExceptionally(mapper).mapExceptionally(copy::add).orElse(copy);
			}
			return new ListStream<>(copy);
		});
	}

	public <R, E extends Exception> R fold(R identity, ExceptionalFunction2<R, T, R, E> mapper) throws StreamException {
		return supplyExceptionally(() -> {
			R current = identity;
			int size = list.size();
			for (int i = 0; i < size; i++) {
				R finalCurrent = current;
				current = get(i).mapExceptionally(t -> mapper.apply(finalCurrent, t))
						.orElse(current);
			}
			return current;
		});
	}

	private <R> R supplyExceptionally(ExceptionalFunction0<R, Exception> supplier) throws StreamException {
		try {
			return supplier.apply();
		} catch (Exception e) {
			throw new StreamException("Failed to process stream.", e);
		}
	}

	public Option<T> head() {
		return list.isEmpty() ? None() : get(0);
	}
}
