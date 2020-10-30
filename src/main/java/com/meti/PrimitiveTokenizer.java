package com.meti;

import java.util.Optional;

public class PrimitiveTokenizer extends StringTokenizer<Type> {
    public PrimitiveTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Type> tokenize() {
        try {
            Primitive type = Primitive.valueOf(content);
            return Optional.of(type);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
