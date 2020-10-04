package com.meti.api.string;

import com.meti.api.collect.Array;
import com.meti.api.collect.CharArray;
import com.meti.api.java.Function1;

import java.util.Arrays;

public class String_ {
    private final Array<Character, char[]> array;

    public String_(Array<Character, char[]> array) {
        this.array = array;
    }

    public static String_ __(String value) {
        var oldArray = value.toCharArray();
        var oldLength = value.length();
        var newLength = oldLength + 1;
        var newArray = Arrays.copyOf(oldArray, newLength);
        newArray[oldLength] = '\0';
        return new String_(new CharArray(newArray, newLength));
    }

    public String_ format(Object... values) {
        return this;
    }

    public <T> T map(Function1<String_, T> function) {
        return function.apply(this);
    }

    public String toNative() {
        return array.length()
                .prepend(0)
                .prepend(array)
                .flatMapStart(Array::toNative)
                .zip(Arrays::copyOfRange)
                .apply(String::new);
    }
}
