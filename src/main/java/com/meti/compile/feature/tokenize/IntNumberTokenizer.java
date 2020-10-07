package com.meti.compile.feature.tokenize;

import com.meti.compile.feature.number.IntNumber;

import java.math.BigInteger;
import java.util.Optional;

public class IntNumberTokenizer extends AbstractTokenizer {
    public IntNumberTokenizer(String value) {
        super(value);
    }

    @Override
    public Optional<Tokenizer.Token> tokenize() {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(new IntNumber(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
