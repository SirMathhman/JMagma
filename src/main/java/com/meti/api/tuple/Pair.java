package com.meti.api.tuple;

import com.meti.api.java.Consumer2;

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
}
