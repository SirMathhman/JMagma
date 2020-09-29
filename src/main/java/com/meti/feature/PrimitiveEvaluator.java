package com.meti.feature;

import java.util.Optional;

public class PrimitiveEvaluator implements TypeEvaluator {
    @Override
    public Optional<Evaluatable<Type>> evaluate(String content) {
        try {
            Type type = Primitive.valueOf(content);
            return Optional.of(() -> type);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
