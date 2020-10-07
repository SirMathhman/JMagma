package com.meti.compile.render.scope;

import com.meti.compile.render.evaluate.AbstractNodeTokenizer;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class VariableTokenizer extends AbstractNodeTokenizer {
    public VariableTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.of(new Variable(content));
    }
}
