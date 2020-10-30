package com.meti;

import java.util.Optional;

public class VariableTokenizer extends StringTokenizer<Node> {
    public VariableTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        return Optional.of(new Variable(content));
    }
}
