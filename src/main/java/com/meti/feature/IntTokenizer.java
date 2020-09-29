package com.meti.feature;

import java.math.BigInteger;
import java.util.Optional;

public class IntTokenizer implements Tokenizer {
    @Override
    public Optional<Tokenizable> create(String content) {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(() -> new Int(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
