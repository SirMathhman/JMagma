package com.meti.api.stream;

import com.meti.api.extern.Function2;

import java.util.function.Function;

@Deprecated
public class ArrayStream<T> {
    private final T[] array;
    private final Function<Integer, T[]> allocator;

    private ArrayStream(T[] array, Function<Integer, T[]> allocator) {
        this.array = array;
        this.allocator = allocator;
    }

    @Deprecated
    public static <T> ArrayStream<T> Stream(T[] array) {
        throw new UnsupportedOperationException();
    }

    public static <T> ArrayStream<T> Stream(T[] array, Function<Integer, T[]> allocator) {
        return new ArrayStream<>(array, allocator);
    }

    public <R> R foldLeft(R identity, Function2<R, T, R> folder) {
        R current = identity;
        for (T t : array) {
            current = folder.apply(current, t);
        }
        return current;
    }
}
