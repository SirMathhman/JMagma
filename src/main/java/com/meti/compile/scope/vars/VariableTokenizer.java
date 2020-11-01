package com.meti.compile.scope.vars;

import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.Node;

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
