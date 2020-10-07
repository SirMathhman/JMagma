package com.meti.compile.feature.primitive;

import com.meti.compile.feature.node.Node;

import java.math.BigInteger;

public class IntNumber implements Node {
    private final BigInteger value;

    public IntNumber(BigInteger value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value.toString();
    }
}
