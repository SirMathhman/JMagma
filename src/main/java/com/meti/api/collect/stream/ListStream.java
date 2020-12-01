package com.meti.api.collect.stream;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.list.MutableList;
import com.meti.api.core.Option;
import com.meti.api.extern.*;

import static com.meti.api.collect.list.ArrayList.ArrayList;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class ListStream<T> implements Stream<T> {
	private final MutableList<T> mutableList;

	private ListStream(MutableList<T> mutableList) {
		this.mutableList = mutableList;
	}

	public static class ListStreams {
		@SafeVarargs
		public static <T> ListStream<T> of(T... values) {
			return ofList(ArrayList(values));
		}

		public static <T> ListStream<T> ofList(MutableList<T> mutableList) {
			return new ListStream<>(mutableList);
		}
	}

	@Override
	public boolean anyMatch(Function1<T, Boolean> predicate) {
		int size = mutableList.size();
		for (int i = 0; i < size; i++) {
			if (get(i)
					.filter(predicate)
					.isPresent()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Stream<T> filter(Function1<T, Boolean> predicate) {
		MutableList<T> copy = mutableList.empty();
		int size = mutableList.size();
		for (int i = 0; i < size; i++) {
			copy = get(i).filter(predicate).mapExceptionally(copy::add).orElse(copy);
		}
		return ListStreams.ofList(copy);
	}

	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) throws StreamException {
		return supplyExceptionally(() -> {
			MutableList<R> copy = mutableList.empty();
			int size = mutableList.size();
			for (int i = 0; i < size; i++) {
				copy = get(i).map(mapper).map(copy::add).orElse(copy);
			}
			return ListStreams.ofList(copy);
		});
	}

	@Override
	public <R> Stream<R> mapExceptionally(ExceptionalFunction1<T, R, ?> mapper) throws StreamException {
		return supplyExceptionally(() -> {
			MutableList<R> copy = mutableList.empty();
			int size = mutableList.size();
			for (int i = 0; i < size; i++) {
				copy = get(i).mapExceptionally(mapper).map(copy::add).orElse(copy);
			}
			return ListStreams.ofList(copy);
		});
	}

	@Override
	public <R, E extends Exception> R foldExceptionally(R identity, ExceptionalFunction2<R, T, R, E> mapper) throws StreamException {
		return supplyExceptionally(() -> {
			R current = identity;
			int size = mutableList.size();
			for (int i = 0; i < size; i++) {
				R finalCurrent = current;
				current = get(i).mapExceptionally(t -> mapper.apply(finalCurrent, t))
						.orElse(current);
			}
			return current;
		});
	}

	@Override
	public <R> R fold(R identity, Function2<R, T, R> mapper) {
		R current = identity;
		int size = mutableList.size();
		for (int i = 0; i < size; i++) {
			R finalCurrent = current;
			current = get(i)
					.map(t -> mapper.apply(finalCurrent, t))
					.orElse(current);
		}
		return current;
	}

	@Override
	public Option<T> head() {
		return mutableList.isEmpty() ? None() : get(0);
	}

	private Option<T> get(int index) {
		try {
			return Some(mutableList.get(index));
		} catch (IndexException e) {
			return None();
		}
	}

	private <R> R supplyExceptionally(ExceptionalFunction0<R, Exception> supplier) throws StreamException {
		try {
			return supplier.apply();
		} catch (Exception e) {
			throw new StreamException("Failed to process stream.", e);
		}
	}
}
