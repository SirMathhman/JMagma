package com.meti.feature;

import java.util.Optional;

public class VariableTokenizer implements Tokenizer {
    @Override
    public Optional<Tokenizable> create(String content) {
        return Optional.of(() -> new Variable(content));
    }
}
