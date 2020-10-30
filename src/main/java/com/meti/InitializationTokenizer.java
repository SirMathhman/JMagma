package com.meti;

import java.util.Optional;
import java.util.function.Function;

public class InitializationTokenizer extends StringTokenizer<Node> {
    public InitializationTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.contains("=")) {
            String identityString = content.substring(0, content.indexOf('=')).trim();
            return new FieldTokenizer(identityString)
                    .tokenize()
                    .filter(field -> field.is(Field.Flag.CONST) || field.is(Field.Flag.LET))
                    .map(this::complete);
        }
        return Optional.empty();
    }

    private Initialization complete(Field field) {
        int separator = content.indexOf('=');
        String valueSlice = content.substring(separator + 1);
        String valueTrim = valueSlice.trim();
        Node value = new ContentNode(valueTrim);
        return new Initialization(field, value);
    }

    private IllegalArgumentException invalidateIdentity(String identityString) {
        String format = "Invalid identity: %s";
        String message = format.formatted(identityString);
        return new IllegalArgumentException(message);
    }
}
