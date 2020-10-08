package com.meti.compile.render.function;

import com.meti.compile.render.evaluate.AbstractNodeTokenizer;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class FunctionTokenizer extends AbstractNodeTokenizer {
    public FunctionTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.empty();
    }
}
