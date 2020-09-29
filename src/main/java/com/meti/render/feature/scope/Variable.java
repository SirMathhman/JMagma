package com.meti.render.feature.scope;

import com.meti.render.evaluate.field.Field;
import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.type.Type;

import java.util.Optional;
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

    @Override
    public boolean is(Group group) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <R> Optional<R> transformContent(Function<String, R> mapping) {
        return Optional.empty();
    }

    @Override
    public Token mapByFields(Function<Field, Field> mapping) {
        return this;
    }

    @Override
    public Token mapByTypes(Function<Type, Type> mapping) {
        return this;
    }
}
