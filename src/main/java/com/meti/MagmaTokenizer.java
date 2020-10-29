package com.meti;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaTokenizer extends CompoundTokenizer {
    public MagmaTokenizer(String value) {
        super(value);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Node>>> streamFactories() {
        return Stream.of(IntTokenizer::new);
    }
}
