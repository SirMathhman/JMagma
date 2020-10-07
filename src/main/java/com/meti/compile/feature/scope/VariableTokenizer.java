package com.meti.compile.feature.scope;

import com.meti.compile.feature.tokenize.AbstractTokenizer;

import java.util.Optional;

public class VariableTokenizer extends AbstractTokenizer {
    public VariableTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Token> evaluate() {
        return Optional.of(new Variable(content));
    }
}
