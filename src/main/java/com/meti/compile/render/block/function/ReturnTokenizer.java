package com.meti.compile.render.block.function;

import com.meti.compile.render.tokenize.AbstractNodeTokenizer;
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
            var value = ContentNode.ContentNode(valueString);
            return Optional.of(Return.Return(value));
        }
        return Optional.empty();
    }
}
