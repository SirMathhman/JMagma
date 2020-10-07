package com.meti.compile.render.scope;

import com.meti.compile.render.evaluate.AbstractNodeEvaluator;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class VariableEvaluator extends AbstractNodeEvaluator {
    public VariableEvaluator(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.of(new Variable(content));
    }
}
