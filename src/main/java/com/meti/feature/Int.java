package com.meti.feature;

import java.math.BigInteger;

public class Int implements Token {
    private final BigInteger integer;

    public Int(BigInteger integer) {
        this.integer = integer;
    }

    @Override
    public String render() {
        return integer.toString();
    }
}
