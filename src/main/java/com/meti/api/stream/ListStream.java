package com.meti.api.stream;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.List;
import com.meti.api.core.Option;
import com.meti.api.memory.Allocator;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.memory.DefaultAllocator.DefaultAllocator_;

public class ListStream<T> implements Stream<T> {
	private final Allocator allocator;
	private final List<T> list;

	private ListStream(Allocator allocator, List<T> list) {
		this.allocator = allocator;
		this.list = list;
	}

	public static <R> Stream<R> ListStream(List<R> list) {
		return ListStream(DefaultAllocator_, list);
	}

	public static <R> Stream<R> ListStream(Allocator allocator, List<R> list) {
		return new ListStream<>(allocator, list);
	}

	@Override
	public <R> Stream<R> flatMapOptionally(Allocator allocator, Function<T, Option<R>> mapper) throws StreamException {
		List<R> current = ArrayList(allocator);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			current = mapper.apply(getInternally(i))
					.map(current::add)
					.orElse(current);
		}
		return ListStream(allocator, current);
	}

	private T getInternally(int i) throws StreamException {
		T value;
		try {
			value = list.get(i);
		} catch (IndexException e) {
			String format = "Failed to get element inside of internal list at index '%d'.";
			String message = format.formatted(i);
			throw new StreamException(message, e);
		}
		return value;
	}

	@Override
	public Stream<T> filter(Predicate<T> predicate) throws StreamException {
		return flatMapOptionally(allocator, t -> predicate.test(t) ? Some(t) : None());
	}

	@Override
	public <R> Stream<R> map(Allocator allocator, Function<T, R> mapper) throws StreamException {
		return flatMapOptionally(allocator, t -> Some(mapper.apply(t)));
	}

	@Override
	public <R> R foldLeft(R identity, BiFunction<R, T, R> folder) throws StreamException {
		R current = identity;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			current = folder.apply(current, getInternally(i));
		}
		return current;
	}
}
