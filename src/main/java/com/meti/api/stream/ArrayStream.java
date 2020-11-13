package com.meti.api.stream;

import com.meti.api.extern.Function2;

import java.util.function.Function;

public class ArrayStream<T> {
    private final T[] array;

    private ArrayStream(T[] array) {
        this.array = array;
    }

    public static <T> ArrayStream<T> Stream(T[] array) {
        return new ArrayStream<T>(array);
    }

    public <R> R foldLeft(R identity, Function2<R, T, R> folder) {
        R current = identity;
        for (T t : array) {
            current = folder.apply(current, t);
        }
        return current;
    }
}
