package com.meti.api.collect;

import com.meti.api.java.Function1;
import com.meti.api.tuple.Unit;

public class IntArray implements Array<Integer, int[]> {
    public static final IntArray Empty = new IntArray(0);
    private final int[] array;
    private final int length;

    public IntArray(int length) {
        this(new int[length], length);
    }

    public IntArray(int[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public static Array<Integer, int[]> range(int from, int to) {
        var length = to - from;
        var array = new IntArray(length);
        for (int i = 0; i < length; i++) {
            array.set(i, from + i);
        }
        return array;
    }

    @Override
    public Stream<Integer> stream() {
        return new Stream<Integer>() {
            @Override
            public <R> Stream<R> map(Function1<Integer, R> function) {
                return null;
            }
        };
    }

    @Override
    public Array<Integer, int[]> copy() {
        return Empty;
    }

    @Override
    public Unit<Integer> apply(int index) {
        return new Unit<>(array[index]);
    }

    @Override
    public Array<Integer, int[]> set(int index, Integer value) {
        array[index] = value;
        return this;
    }

    @Override
    public Unit<Integer> length() {
        return new Unit<>(length);
    }

    @Override
    public Unit<int[]> toNative() {
        return new Unit<>(array);
    }

    @Override
    public <R> Unit<R> map(Function1<Array<Integer, int[]>, R> function) {
        return new Unit<>(function.apply(this));
    }
}
