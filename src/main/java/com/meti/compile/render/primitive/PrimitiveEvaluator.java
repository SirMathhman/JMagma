package com.meti.compile.render.primitive;

import com.meti.compile.render.type.Type;
import com.meti.compile.render.type.TypeEvaluator;

import java.util.Optional;

public class PrimitiveEvaluator implements TypeEvaluator {
    private final String content;

    public PrimitiveEvaluator(String content) {
        this.content = content;
    }

    @Override
    public Optional<Type> evaluate() {
        try {
            var asUpper = content.toUpperCase();
            var type = Primitive.valueOf(asUpper);
            return Optional.of(type);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
