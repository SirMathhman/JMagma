package com.meti.feature;

import java.util.Optional;

public class DeclareEvaluator implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        int separator = content.indexOf('=');
        if (separator != -1) {
            return Optional.of(new DeclareEvaluatable(content, separator));
        } else {
            return Optional.empty();
        }
    }

    private static class DeclareEvaluatable implements Evaluatable<Token> {
        private final String content;
        private final int separator;

        public DeclareEvaluatable(String content, int separator) {
            this.content = content;
            this.separator = separator;
        }

        @Override
        public Token complete() {
            String identityString = content.substring(0, separator);
            String identityTrimmed = identityString.trim();
            Field identity = FieldEvaluator.FieldEvaluator.evaluate(identityTrimmed)
                    .orElseThrow()
                    .complete();
            String valueString = content.substring(separator + 1);
            String valueTrimmed = valueString.trim();
            Token value = new Content(valueTrimmed);
            return new Declaration(identity, value);
        }
    }
}
