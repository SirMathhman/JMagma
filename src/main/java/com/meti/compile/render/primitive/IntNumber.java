package com.meti.compile.render.primitive;

import com.meti.compile.render.node.*;

import java.math.BigInteger;

public class IntNumber implements EmptyNode, LeafNode, UnidentifiedNode, UnfieldedNode {
    private final BigInteger value;

    public IntNumber(BigInteger value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value.toString();
    }

    @Override
    public boolean is(Group group) {
        return group == Group.IntNumber;
    }

    @Override
    public <T> T value(Class<T> clazz) {
        return clazz.cast(value);
    }

    @Override
    public Node withValue(Object value) {
        return new IntNumber((BigInteger) value);
    }
}
