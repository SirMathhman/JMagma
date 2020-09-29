package com.meti.render.feature.primitive;

import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.type.Type;
import com.meti.render.evaluate.field.Field;

import java.math.BigInteger;
import java.util.Optional;
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
