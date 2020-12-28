package com.meti.api.core;

import static com.meti.api.core.None.None;

public record Some<T>(T value) implements Option<T> {
	public static <T> Option<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return Some(mapper.apply(value));
	}

	@Override
	public void ifPresent(Consumer<T> consumer) {
		consumer.apply(value);
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(EF1<T, R, E> mapper) throws E {
		return Some(mapper.apply(value));
	}

	@Override
	public <E extends Exception> T orElseThrow(Supplier<E> supplier) throws E {
		return value;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public <R> Option<R> flatMap(F1<T, Option<R>> mapper) {
		return mapper.apply(value);
	}

	@Override
	public Option<T> filter(F1<T, Boolean> predicate) {
		return predicate.apply(value) ? this : None();
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		return value;
	}
}
