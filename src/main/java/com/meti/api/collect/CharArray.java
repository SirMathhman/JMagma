package com.meti.api.collect;

import com.meti.api.java.CharFunction;

public class CharArray {
    private final char[] array;
    private final int length;

    public CharArray(int length) {
        this(new char[length], length);
    }

    public CharArray(char[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public <T> T apply(int index, CharFunction<T> function) {
        return function.apply(array[index]);
    }

    public CharArray set(int index, char value) {
        array[index] = value;
        return this;
    }

    public int length() {
        return length;
    }
}
