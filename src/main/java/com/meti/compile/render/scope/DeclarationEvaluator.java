package com.meti.compile.render.scope;

import com.meti.compile.render.evaluate.AbstractNodeEvaluator;
import com.meti.compile.render.field.FieldEvaluator;
import com.meti.compile.render.node.Node;

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
