package com.meti.feature;

import java.util.function.Function;

public interface Leaf extends Token {
    @Override
    default Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }

    @Override
    default Token mapByFields(Function<Field, Field> mapping) {
        return this;
    }

    @Override
    default Token mapByTypes(Function<Type, Type> mapping) {
        return this;
    }
}
