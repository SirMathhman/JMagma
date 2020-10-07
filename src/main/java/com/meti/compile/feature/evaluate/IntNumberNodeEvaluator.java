package com.meti.compile.feature.evaluate;

import com.meti.compile.feature.node.Node;
import com.meti.compile.feature.primitive.IntNumber;

import java.math.BigInteger;
import java.util.Optional;

public class IntNumberNodeEvaluator extends AbstractNodeEvaluator {
    public IntNumberNodeEvaluator(String value) {
        super(value);
    }

    @Override
    public Optional<Node> evaluate() {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(new IntNumber(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
