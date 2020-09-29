package com.meti.feature;

import java.util.Optional;

public class DeclareEvaluatable implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        Field identity = FieldEvaluator.FieldEvaluator.evaluate(content.trim())
                .orElseThrow()
                .complete();
        if (identity.isFlagged(Field.Flag.CONST) || identity.isFlagged(Field.Flag.LET)) {
            return Optional.of(new EvaluatableImpl(identity));
        } else {
            return Optional.empty();
        }
    }

    private static class EvaluatableImpl implements Evaluatable<Token> {
        private final Field identity;

        public EvaluatableImpl(Field identity) {
            this.identity = identity;
        }

        @Override
        public Token complete() {
            return new Declaration(identity);
        }
    }
}
