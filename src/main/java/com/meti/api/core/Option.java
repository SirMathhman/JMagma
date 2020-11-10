package com.meti.api.core;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Option<T> {
    Option<T> peek(Consumer<T> consumer);

    T orElseSupply(Supplier<T> supplier);

    <R> Option<R> flatMap(Function<T, Option<R>> function);
}
