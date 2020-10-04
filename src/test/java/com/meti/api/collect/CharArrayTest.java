package com.meti.api.collect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.CharArray.CharArray;

class CharArrayTest {

    @Test
    void apply() {
        CharArray(1)
                .set(0, 'x')
                .apply(0)
                .prepend('x')
                .accept(Assertions::assertEquals);
    }

    @Test
    void set() {
        CharArray(1)
                .set(0, 'x')
                .toNative()
                .prepend(new char[]{'x'})
                .accept(Assertions::assertArrayEquals);
    }

    @Test
    void length() {
        CharArray(3)
                .length()
                .prepend(3)
                .accept(Assertions::assertEquals);
    }

    @Test
    void toNative() {
        CharArray().toNative()
                .prepend(new char[0])
                .accept(Assertions::assertArrayEquals);
    }
}