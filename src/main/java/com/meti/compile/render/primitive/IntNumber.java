package com.meti.compile.render.primitive;

import com.meti.compile.render.scope.EmptyNode;
import com.meti.compile.render.scope.UnfieldedNode;

import java.math.BigInteger;

public class IntNumber implements EmptyNode, UnfieldedNode {
    private final BigInteger value;

    public IntNumber(BigInteger value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value.toString();
    }
}
