package com.meti.render.evaluate.token;

import com.meti.render.evaluate.type.Type;

import java.util.function.Function;

public interface UntypedToken extends Token {
    @Override
    default Token mapByTypes(Function<Type, Type> mapping) {
        return this;
    }
}
