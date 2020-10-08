package com.meti.compile.render.function;

import com.meti.compile.render.evaluate.AbstractNodeTokenizer;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class ReturnTokenizer extends AbstractNodeTokenizer {
    public ReturnTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("return ")) {
            var valueString = content.substring(7).trim();
            var value = new ContentNode(valueString);
            return Optional.of(new Return(value));
        }
        return Optional.empty();
    }
}
