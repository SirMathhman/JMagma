package com.meti.compile.feature.scope;

import com.meti.compile.feature.evaluate.AbstractNodeEvaluator;
import com.meti.compile.feature.field.FieldEvaluator;
import com.meti.compile.feature.node.Node;

import java.util.Optional;

public class DeclarationEvaluator extends AbstractNodeEvaluator {
    public DeclarationEvaluator(String content) {
        super(content);
    }


    @Override
    public Optional<Node> evaluate() {
        return new FieldEvaluator(content)
                .evaluate().map(Declaration::new);
    }
}
