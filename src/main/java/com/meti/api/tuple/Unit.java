package com.meti.api.tuple;

import com.meti.api.java.Consumer1;
import com.meti.api.java.Function1;

public class Unit<T> {
    private final T value;

    public Unit(T value) {
        this.value = value;
    }

    public void accept(Consumer1<T> consumer) {
        consumer.accept(value);
    }

    public <B> Pair<B, T> prepend(B other) {
        return new Pair<>(other, value);
    }

    public <R> Unit<R> map(Function1<T, R> function) {
        return new Unit<>(function.apply(value));
    }

    public <B> Pair<T, B> append(B other) {
        return new Pair<>(value, other);
    }

    public <R> R apply(Function1<T, R> function) {
        return function.apply(value);
    }

    public <R> Unit<R> flatMap(Function1<T, Unit<R>> function) {
        return function.apply(value);
    }

    public <B> Pair<T, B> unfold(Function1<T, B> function) {
        return map(function).prepend(value);
    }
}
