package com.meti.api.string;

import com.meti.api.tuple.Unit;

import static com.meti.api.collect.CharArray.CharArray;

public class StringBuffer {
    public static StringBuffer Empty = new StringBuffer(String_.Empty);
    private final String_ value;

    public StringBuffer(String_ value) {
        this.value = value;
    }

    public static StringBuffer singleton(char c) {
        var array = CharArray(2)
                .set(0, c);
        return new StringBuffer(new String_(array));
    }

    public Unit<Integer> length() {
        return value.length();
    }

    public StringBuffer append(StringBuffer other) {
        var newLength = length().apply(other.length()::append)
                .apply(Integer::sum);
        var newString = new String_(CharArray(newLength));
        return new StringBuffer(newString);
    }

    public String_ complete() {
        return value;
    }
}
