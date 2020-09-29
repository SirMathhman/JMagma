package com.meti.feature;

import java.util.function.Function;

public class Variable implements Token {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value;
    }

    @Override
    public Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }
}
