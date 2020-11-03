package com.meti.compile.primitive.ints;

import com.meti.compile.Node;

import java.math.BigInteger;
import java.util.Objects;

public class Int implements Node {
    private final BigInteger value;

    public Int(BigInteger value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Int anInt = (Int) o;
        return Objects.equals(value, anInt.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String render() {
        return value.toString();
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Int;
    }
}
