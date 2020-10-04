package com.meti.api.tuple;

import com.meti.api.java.Consumer2;
import com.meti.api.java.Function1;
import com.meti.api.java.Function2;

public class Pair<A, B> {
    private final A start;
    private final B end;

    public Pair(A start, B end) {
        this.start = start;
        this.end = end;
    }

    public void accept(Consumer2<A, B> consumer) {
        consumer.accept(start, end);
    }

    public <C> Triplet<C, A, B> prepend(C value) {
        return new Triplet<>(value, start, end);
    }

    public <C> Triplet<A, B, C> append(C value) {
        return new Triplet<>(start, end, value);
    }

    private Unit<A> start() {
        return new Unit<>(start);
    }

    public Unit<B> end() {
        return new Unit<>(end);
    }

    public <R> Pair<A, R> mapEnd(Function1<B, R> function) {
        return end()
                .map(function)
                .prepend(start);
    }

    public <C> Triplet<A, C, B> inpend(C value) {
        return new Triplet<>(start, value, end);
    }

    public <R> R apply(Function2<A, B, R> function) {
        return function.apply(start, end);
    }
}
