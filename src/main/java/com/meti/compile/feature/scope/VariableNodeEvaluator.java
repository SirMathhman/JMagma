package com.meti.compile.feature.scope;

import com.meti.compile.feature.evaluate.AbstractNodeEvaluator;
import com.meti.compile.feature.node.Node;

import java.util.Optional;

public class VariableNodeEvaluator extends AbstractNodeEvaluator {
    public VariableNodeEvaluator(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.of(new Variable(content));
    }
}
