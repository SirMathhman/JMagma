package com.meti.api.collect;

import com.meti.api.tuple.Unit;

public interface Array<T, A> {
    Unit<T> apply(int index);

    Array<T, A> set(int index, T value);

    Unit<Integer> length();

    Unit<A> toNative();
}
