package com.meti.render.feature.scope;

import com.meti.render.evaluate.Evaluatable;
import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.TokenEvaluator;

import java.util.Optional;

public class VariableEvaluator implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        return Optional.of(() -> new Variable(content));
    }
}
