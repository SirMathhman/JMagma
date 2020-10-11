package com.meti.compile.render.scope;

import com.meti.compile.render.tokenize.AbstractNodeTokenizer;
import com.meti.compile.render.field.FieldTokenizer;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class DeclarationTokenizer extends AbstractNodeTokenizer {
    public DeclarationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return new FieldTokenizer(content)
                .evaluate().map(Declaration::new);
    }
}
