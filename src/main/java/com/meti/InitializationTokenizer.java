package com.meti;

import java.util.Optional;

public class InitializationTokenizer extends StringTokenizer<Node> {
    public InitializationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.contains("=")) {
            String identityString = content.substring(0, content.indexOf('=')).trim();
            Field identity = new FieldTokenizer(identityString)
                    .tokenize()
                    .orElseThrow(() -> invalidateIdentity(identityString));
            String valueString = content.substring(content.indexOf('=') + 1).trim();
            Node value = new ContentNode(valueString);
            return Optional.of(new Initialization(identity, value));
        }
        return Optional.empty();
    }

    private IllegalArgumentException invalidateIdentity(String identityString) {
        String format = "Invalid identity: %s";
        String message = format.formatted(identityString);
        return new IllegalArgumentException(message);
    }
}
