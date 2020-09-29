package com.meti.render.feature.primitive;

import com.meti.render.evaluate.token.Token;
import com.meti.render.evaluate.token.TokenEvaluator;
import com.meti.render.evaluate.type.Type;
import com.meti.render.evaluate.Evaluatable;
import com.meti.render.evaluate.field.Field;

import java.util.Optional;
import java.util.function.Function;

public class FloatEvaluator implements TokenEvaluator {
    @Override
    public Optional<Evaluatable<Token>> evaluate(String content) {
        try {
            float value = Float.parseFloat(content);
            return Optional.of(() -> new Float_(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static class Float_ implements Token {
        private final float value;

        public Float_(float value) {
            this.value = value;
        }

        @Override
        public String render() {
            return String.valueOf(value);
        }

        @Override
        public Token mapByChildren(Function<Token, Token> mapping) {
            return this;
        }

        @Override
        public boolean is(Group group) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <R> Optional<R> transformContent(Function<String, R> mapping) {
            return Optional.empty();
        }

        @Override
        public Token mapByFields(Function<Field, Field> mapping) {
            return this;
        }

        @Override
        public Token mapByTypes(Function<Type, Type> mapping) {
            return this;
        }
    }
}
