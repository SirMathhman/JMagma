package com.meti.api.nulls;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class None<T> implements Option<T> {
    private None() {
    }

    public static <T> None<T> None() {
        return new None<>();
    }

    @Override
    public Option<T> peek(Consumer<T> consumer) {
        return None();
    }

    @Override
    public T orElseSupply(Supplier<T> supplier) {
        return supplier.get();
    }

    @Override
    public <R> Option<R> map(Function<T, Option<R>> function) {
        return None();
    }
}
