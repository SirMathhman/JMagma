package com.meti.api.stream;

import com.meti.api.core.Option;
import com.meti.api.memory.Allocator;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.meti.api.memory.DefaultAllocator.DefaultAllocator_;

public interface Stream<T> {
	default <R> Stream<R> flatMapOptionally(Function<T, Option<R>> mapper) throws StreamException {
		return flatMapOptionally(DefaultAllocator_, mapper);
	}

	<R> Stream<R> flatMapOptionally(Allocator allocator, Function<T, Option<R>> mapper) throws StreamException;

	Stream<T> filter(Predicate<T> predicate) throws StreamException;

	default <R> Stream<R> map(Function<T, R> mapper) throws StreamException {
		return map(DefaultAllocator_, mapper);
	}

	<R> Stream<R> map(Allocator allocator, Function<T, R> mapper) throws StreamException;

	<R> R foldLeft(R identity, BiFunction<R, T, R> folder) throws StreamException;
}
