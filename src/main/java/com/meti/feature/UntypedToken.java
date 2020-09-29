package com.meti.feature;

import java.util.function.Function;

public interface UntypedToken extends Token {
    @Override
    default Token mapByTypes(Function<Type, Type> mapping) {
        return this;
    }
}
