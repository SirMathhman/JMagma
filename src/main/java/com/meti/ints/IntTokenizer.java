package com.meti.ints;

import com.meti.Node;
import com.meti.AbstractTokenizer;

import java.math.BigInteger;
import java.util.Optional;

public class IntTokenizer extends AbstractTokenizer<Node> {
    public IntTokenizer(String value) {
        super(value);
    }

    @Override
    public Optional<Node> tokenize() {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(new Int(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
