package com.meti.compile.render.primitive;

import com.meti.compile.render.type.Type;
import com.meti.compile.render.type.TypeTokenizer;

import java.util.Optional;

public class PrimitiveTokenizer implements TypeTokenizer {
    private final String content;

    public PrimitiveTokenizer(String content) {
        this.content = content;
    }

    @Override
    public Optional<Type> evaluate() {
        try {
            var type = Primitive.valueOf(content);
            return Optional.of(type);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
