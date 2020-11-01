package com.meti.compile.primitive.chars;

import com.meti.compile.Node;
import com.meti.compile.tokenize.AbstractTokenizer;

import java.util.Optional;

public class CharTokenizer extends AbstractTokenizer<Node> {
    public CharTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("'") && content.endsWith("'")) {
            char c = content.charAt(1);
            return Optional.of(new Char(c));
        } else {
            return Optional.empty();
        }
    }
}
