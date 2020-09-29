package com.meti.render.feature.scope;

import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.TokenEvaluator;
import com.meti.render.feature.content.Content;
import com.meti.render.evaluate.Evaluatable;
import com.meti.render.evaluate.field.Field;
import com.meti.render.evaluate.field.FieldEvaluator;

import java.util.Optional;

public class InitializationEvaluatable implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        int separator = content.indexOf('=');
        if (separator == -1) return Optional.empty();
        String substring = content.substring(0, separator);
        String trim = substring.trim();
        Field identity = FieldEvaluator.FieldEvaluator.evaluate(trim)
                .orElseThrow()
                .complete();
        if (identity.isFlagged(Field.Flag.CONST) || identity.isFlagged(Field.Flag.LET)) {
            return Optional.of(new EvaluatableImpl(content, separator, identity));
        } else {
            return Optional.empty();
        }
    }

    private static class EvaluatableImpl implements Evaluatable<Token> {
        private final String content;
        private final int separator;
        private final Field identity;

        public EvaluatableImpl(String content, int separator, Field identity) {
            this.content = content;
            this.separator = separator;
            this.identity = identity;
        }

        @Override
        public Token complete() {
            String valueString = content.substring(separator + 1);
            String valueTrimmed = valueString.trim();
            Token value = new Content(valueTrimmed);
            return new Initialization(identity, value);
        }
    }
}
