package com.meti.compile.tokenize;

import java.util.Optional;

public interface Tokenizer<T> {
    Optional<T> tokenize();
}
