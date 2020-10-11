package com.meti.compile.render.primitive;

import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.node.Node;

import java.math.BigInteger;

public class IntNumber implements LeafNode, Node {
    private final BigInteger value;

    private IntNumber(BigInteger value) {
        this.value = value;
    }

    public static IntNumber Int(long value) {
        return Int(BigInteger.valueOf(value));
    }

    public static IntNumber Int(BigInteger value) {
        return new IntNumber(value);
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
        return Int((BigInteger) value);
    }
}
