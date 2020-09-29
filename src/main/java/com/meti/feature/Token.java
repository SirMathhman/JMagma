package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

public interface Token extends Renderable {
    Token mapByChildren(Function<Token, Token> mapping);

    Token mapByFields(Function<Field, Field> mapping);

    Token mapByTypes(Function<Type, Type> mapping);

    <R> Optional<R> transformContent(Function<String, R> mapping);

    boolean is(Group group);

    enum Group {
        Content,

        Int,
        Float,
        Declaration, Initialization, Variable
    }
}
