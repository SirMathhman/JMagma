package com.meti.render.evaluate.token;

import com.meti.render.evaluate.field.Field;

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
