package com.meti.tokenize;

import java.util.Optional;

public interface Tokenizer<T> {
    Optional<T> tokenize();
}
