package com.meti.api.tuple;

import com.meti.api.java.Function1;
import com.meti.api.java.Function3;

public class Triplet<A, B, C> {
    private final A start;
    private final B middle;
    private final C end;

    public Triplet(A start, B middle, C end) {
        this.start = start;
        this.middle = middle;
        this.end = end;
    }

    public Unit<A> start() {
        return new Unit<>(start);
    }

    public <R> Unit<R> zip(Function3<A, B, C, R> function) {
        return new Unit<>(function.apply(start, middle, end));
    }

    public <R> Triplet<R, B, C> flatMapStart(Function1<A, Unit<R>> function) {
        return function.apply(start)
                .append(middle)
                .append(end);
    }

    public <R> Triplet<R, B, C> mapStart(Function1<A, R> function) {
        return start()
                .map(function)
                .append(middle)
                .append(end);
    }
}
