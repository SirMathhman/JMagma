package com.meti.api.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.meti.api.core.None.None;

public class Some<T> implements Option<T> {
	private final T value;

	private Some(T value) {
		this.value = value;
	}

	public static <T> Option<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public Optional<T> toJava() {
		return Optional.of(value);
	}

	@Override
	public Option<T> filter(Predicate<T> predicate) {
		if (predicate.test(value)) return this;
		else return None();
	}

	@Override
	public Option<T> peek(Consumer<T> consumer) {
		consumer.accept(value);
		return this;
	}

	@Override
	public T orElseSupply(Supplier<T> supplier) {
		return value;
	}

	@Override
	public <R> Option<R> flatMap(Function<T, Option<R>> function) {
		return function.apply(value);
	}

	@Override
	public <R> Option<R> map(Function<T, R> mapper) {
		return Some(mapper.apply(value));
	}
}
