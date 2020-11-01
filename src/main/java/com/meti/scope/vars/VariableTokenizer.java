package com.meti.scope.vars;

import com.meti.tokenize.AbstractTokenizer;
import com.meti.Node;

import java.util.Optional;

public class VariableTokenizer extends AbstractTokenizer<Node> {
    public VariableTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        return Optional.of(new Variable(content));
    }
}
