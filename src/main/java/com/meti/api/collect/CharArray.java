package com.meti.api.collect;

import com.meti.api.tuple.Unit;

import static com.meti.api.string.String_.__;

public class CharArray implements Array<Character, char[]> {
    public static final Array<Character, char[]> Empty = CharArray(0);
    private final char[] array;
    private final int length;

    private CharArray(int length) {
        this(new char[length], length);
    }

    public CharArray(char[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public static Array<Character, char[]> CharArray() {
        return Empty;
    }

    public static Array<Character, char[]> CharArray(int length) {
        return new CharArray(length);
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
}
