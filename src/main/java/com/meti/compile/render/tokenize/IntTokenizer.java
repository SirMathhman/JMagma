package com.meti.compile.render.tokenize;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.IntNumber;

import java.math.BigInteger;
import java.util.Optional;

public class IntTokenizer extends AbstractNodeTokenizer {
    public IntTokenizer(String value) {
        super(value);
    }

    @Override
    public Optional<Node> evaluate() {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(IntNumber.Int(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
