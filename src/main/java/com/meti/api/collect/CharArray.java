package com.meti.api.collect;

import com.meti.api.java.Function1;
import com.meti.api.tuple.Unit;

import static com.meti.api.string.String_.__;

public class CharArray implements Array<Character, char[]> {
    public static final Array<Character, char[]> Empty = CharArray(0);
    private final char[] array;
    private final int length;

    public CharArray(char[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public static Array<Character, char[]> CharArray() {
        return Empty;
    }

    public static Array<Character, char[]> CharArray(int length) {
        return new CharArray(new char[length], length);
    }

    @Override
    public Stream<Character> stream() {
        return null;
    }

    @Override
    public Array<Character, char[]> copy() {
        return null;
    }

    @Override
    public Unit<Character> apply(int index) {
        checkBounds(index);
        return new Unit<>(array[index]);
    }

    @Override
    public Array<Character, char[]> set(int index, Character value) {
        checkBounds(index);
        array[index] = value;
        return this;
    }

    @Override
    public Unit<Integer> length() {
        return new Unit<>(length);
    }

    private void checkBounds(int index) {
        if (index < 0) {
            throw __("Index '%i16' must be more than 0.")
                    .format(index)
                    .map(IndexException::new);
        }
        if (index >= length) {
            throw __("Index '%i16' must be less than the overall length.")
                    .format(index)
                    .map(IndexException::new);
        }
    }

    @Override
    public Unit<char[]> toNative() {
        return new Unit<>(array);
    }

    @Override
    public <R> Unit<R> map(Function1<Array<Character, char[]>, R> function) {
        return new Unit<Array<Character, char[]>>(this).map(function);
    }
}
