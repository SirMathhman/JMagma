package com.meti.feature;

import java.util.Optional;
import java.util.function.Function;

public interface Token {
    Token mapByChildren(Function<Token, Token> mapping);

    <R> Optional<R> transformContent(Function<String, R> mapping);

    String render();

    boolean is(Group group);

    enum Group {
        Content,

        Int,
        Float,
        Variable
    }
}
