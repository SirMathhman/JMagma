package com.meti.render.feature.primitive;

import com.meti.render.evaluate.type.Type;
import com.meti.render.evaluate.type.TypeEvaluator;
import com.meti.render.evaluate.Evaluatable;

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
