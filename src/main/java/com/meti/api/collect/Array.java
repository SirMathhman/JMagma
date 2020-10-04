package com.meti.api.collect;

import com.meti.api.java.Function1;
import com.meti.api.tuple.Unit;

public interface Array<T, A> {
    Stream<T> stream();

    Array<T, A> copy();

    Unit<T> apply(int index);

    Array<T, A> set(int index, T value);

    Unit<Integer> length();

    Unit<A> toNative();

    <R> Unit<R> map(Function1<Array<T, A>, R> function);
}
