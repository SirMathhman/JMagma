package com.meti.api.core;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Option<T> {
	Optional<T> toJava();

	Option<T> filter(Predicate<T> predicate);

	T orElseSupply(Supplier<T> supplier);

	<R> Option<R> flatMap(Function<T, Option<R>> function);

	<R> Option<R> map(Function<T, R> mapper);

	T orElse(T other);
}
