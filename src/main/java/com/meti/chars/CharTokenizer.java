package com.meti.chars;

import com.meti.Node;
import com.meti.StringTokenizer;

import java.util.Optional;

public class CharTokenizer extends StringTokenizer<Node> {
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
