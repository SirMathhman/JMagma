package com.meti.compile.render.scope;

import com.meti.compile.render.tokenize.AbstractNodeTokenizer;
import com.meti.compile.render.field.Field;
import com.meti.compile.render.field.FieldTokenizer;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;

import java.util.Optional;

public class InitializationTokenizer extends AbstractNodeTokenizer {
    private static final String Format = "Invalid left-hand side of initialization: %s";

    public InitializationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        var separator = content.indexOf("=");
        if (separator == -1) {
            return Optional.empty();
        } else {
            var identity = evaluateIdentity(content.substring(0, separator));
            var value = evaluateValue(content.substring(separator + 1));
            return Optional.of(new Initialization(identity, value));
        }
    }

    private ContentNode evaluateValue(String right) {
        var trim = right.trim();
        return ContentNode.ContentNode(trim);
    }

    private Field evaluateIdentity(String left) {
        return Optional.of(left)
                .map(String::trim)
                .map(FieldTokenizer::new)
                .flatMap(FieldTokenizer::evaluate)
                .orElseThrow(() -> createInvalidIdentity(left));
    }

    private IllegalStateException createInvalidIdentity(String identityString) {
        var message = Format.formatted(identityString);
        return new IllegalStateException(message);
    }
}
