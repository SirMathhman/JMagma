package com.meti.compile.primitive;

import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.Type;

import java.util.Optional;

public class PrimitiveTokenizer extends AbstractTokenizer<Type> {
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
