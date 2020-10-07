package com.meti.compile.feature.tokenize;

import java.util.Optional;

public interface Tokenizer {
    Optional<Token> tokenize();

    interface Token {
        String render();
    }
}
