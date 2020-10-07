package com.meti.compile.render.primitive;

import com.meti.compile.render.node.EmptyNode;
import com.meti.compile.render.node.LeafNode;
import com.meti.compile.render.scope.UnfieldedNode;
import com.meti.compile.render.scope.UnidentifiedNode;

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
}
