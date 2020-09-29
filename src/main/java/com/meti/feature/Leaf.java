package com.meti.feature;

import java.util.function.Function;

public interface Leaf extends Token {
    @Override
    default Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }
}
