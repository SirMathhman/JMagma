package com.meti.feature;

import java.util.function.Function;

public interface Token {
    Token mapByChildren(Function<Token, Token> mapping);

    String render();
}
