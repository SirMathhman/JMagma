package com.meti.feature;

import java.util.function.Function;

public interface LeafToken extends UntypedToken {
    @Override
    default Token mapByChildren(Function<Token, Token> mapping) {
        return this;
    }

    @Override
    default Token mapByFields(Function<Field, Field> mapping) {
        return this;
    }
}
