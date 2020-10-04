package com.meti.api.string;

import com.meti.api.collect.Array;
import com.meti.api.collect.CharArray;
import com.meti.api.collect.IntArray;
import com.meti.api.java.Function1;
import com.meti.api.tuple.Unit;

import java.util.Arrays;

public class String_ {
    public static String_ Empty = new String_(CharArray.Empty);
    private final Array<Character, char[]> array;

    public String_(Array<Character, char[]> array) {
        this.array = hasNull(array) ? array : formatNull(array);
    }

    public static String_ __(String value) {
        var oldArray = value.toCharArray();
        var oldLength = value.length();
        var newLength = oldLength + 1;
        var newArray = Arrays.copyOf(oldArray, newLength);
        newArray[oldLength] = '\0';
        return new String_(new CharArray(newArray, newLength));
    }

    private Array<Character, char[]> formatNull(Array<Character, char[]> array) {
        return array.length()
                .map(length -> length - 1)
                .append('\0')
                .apply(array::set);
    }

    private boolean hasNull(Array<Character, char[]> array) {
        return array.length()
                .map(length -> length - 1)
                .flatMap(array::apply)
                .apply(value -> value.equals('\0'));
    }

    public String_ slice(int from, int to) {
        return IntArray.range(from, to)
                .stream()
                .map(this::charAsBuffer)
                .reduce(StringBuffer::append)
                .complete();
    }

    private StringBuffer charAsBuffer(int index) {
        return array.apply(index).apply(StringBuffer::singleton);
    }

    //te%i16st
    public String_ format(Object... values) {

        return this;
    }

    public <T> T map(Function1<String_, T> function) {
        return function.apply(this);
    }

    public Unit<Array<Character, char[]>> array() {
        return new Unit<>(array);
    }

    public String __() {
        return array.length()
                .prepend(0)
                .prepend(array)
                .flatMapStart(Array::toNative)
                .zip(Arrays::copyOfRange)
                .map(String::new)
                .unfold(String::length)
                .mapEnd(value -> value - 1)
                .inpend(0)
                .apply(String::substring);
    }

    public Unit<Integer> length() {
        return array.length().map(value -> value - 1);
    }
}
