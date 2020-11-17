package com.meti.api.core;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<>();
	}

	@Override
	public Optional<T> toJava() {
		return Optional.empty();
	}

	@Override
	public Option<T> filter(Predicate<T> predicate) {
		return None();
	}

	@Override
	public T orElseSupply(Supplier<T> supplier) {
		return supplier.get();
	}

	@Override
	public <R> Option<R> flatMap(Function<T, Option<R>> function) {
		return None();
	}

	@Override
	public <R> Option<R> map(Function<T, R> mapper) {
		return None();
	}

	@Override
	public T orElse(T other) {
		return other;
	}
}
