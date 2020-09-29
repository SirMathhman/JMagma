package com.meti.render.feature.primitive;

import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.TokenEvaluator;
import com.meti.render.evaluate.Evaluatable;

import java.math.BigInteger;
import java.util.Optional;

public class IntEvaluator implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        try {
            BigInteger value = new BigInteger(content);
            return Optional.of(() -> new Int(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
