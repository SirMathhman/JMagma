package com.meti;

import java.math.BigInteger;

class Int implements Node {
    private final BigInteger value;

    public Int(BigInteger value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value.toString();
    }

    @Override
    public boolean is(Group group) {
        throw new UnsupportedOperationException();
    }
}
