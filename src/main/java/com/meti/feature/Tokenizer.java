package com.meti.feature;

import java.util.Optional;

public interface Tokenizer {
    Optional<Tokenizable> create(String content);
}
