package com.meti.compile.primitive.ints;

import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;

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
