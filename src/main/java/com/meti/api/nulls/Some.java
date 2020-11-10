package com.meti.api.nulls;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Some<T> implements Option<T> {
    private final T value;

    private Some(T value) {
        this.value = value;
    }

    public static <T> Some<T> Some(T value) {
        return new Some<T>(value);
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
    public <R> Option<R> map(Function<T, Option<R>> function) {
        return function.apply(value);
    }
}
