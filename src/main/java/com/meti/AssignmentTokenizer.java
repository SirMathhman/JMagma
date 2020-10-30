package com.meti;

import java.util.Optional;

public class AssignmentTokenizer extends StringTokenizer<Node> {
    public AssignmentTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.contains("=")) {
            int index = content.indexOf('=');

            String toString = content.substring(0, index);
            String toTrimmed = toString.trim();
            Node to = new ContentNode(toTrimmed);

            String fromString = content.substring(index + 1);
            String fromTrimmed = fromString.trim();
            Node from = new ContentNode(fromTrimmed);

            return Optional.of(new Assignment(to, from));
        }
        return Optional.empty();
    }
}
