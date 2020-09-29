package com.meti.feature;

import java.util.Optional;

public class VariableEvaluator implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        return Optional.of(() -> new Variable(content));
    }
}
