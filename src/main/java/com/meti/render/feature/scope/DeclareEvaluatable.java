package com.meti.render.feature.scope;

import com.meti.render.evaluate.Evaluatable;
import com.meti.render.evaluate.field.Field;
import com.meti.render.evaluate.field.FieldEvaluator;
import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.TokenEvaluator;

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
