package com.meti.feature;

import java.math.BigInteger;
import java.util.function.Function;

public class Int implements Token {
    private final BigInteger integer;

    public Int(BigInteger integer) {
        this.integer = integer;
    }

    @Override
    public String render() {
        return integer.toString();
    }

    @Override
    public Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }
}
