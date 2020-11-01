package com.meti.primitive.chars;

import com.meti.Node;
import com.meti.tokenize.AbstractTokenizer;

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
