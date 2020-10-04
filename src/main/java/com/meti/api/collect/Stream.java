package com.meti.api.collect;

import com.meti.api.java.Function1;
import com.meti.api.java.Function2;

public interface Stream<T> {
    <R> Stream<R> map(Function1<T, R> function);

    T reduce(Function2<T, T, T> function);
}
