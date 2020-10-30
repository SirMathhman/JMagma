package com.meti;

import java.util.function.Function;
import java.util.stream.Stream;

public class TypeTokenizer extends CompoundTokenizer<Type> {
    TypeTokenizer(String content) {
        super(content);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Type>>> streamFactories() {
        return Stream.of(PrimitiveTokenizer::new);
    }
}
